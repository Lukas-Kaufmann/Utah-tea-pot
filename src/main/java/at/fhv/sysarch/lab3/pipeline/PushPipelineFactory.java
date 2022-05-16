package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.filter.*;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec4;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

public class PushPipelineFactory {

    public static void connectFilters(IFilter filter1, IFilter filter2) {
        Pipe pipe = new Pipe();
        pipe.setSuccessor(filter2);
        filter1.setPipeSuccessor(pipe);
    }

    public static void chainFilters(IFilter... filters) {
        if (filters.length <= 1) {
            return;
        }
        for (int i = 0; i < filters.length - 1; i+=1) {
            connectFilters(filters[i], filters[i+1]);
        }
    }

    public static AnimationTimer createPipeline(PipelineData pd) {
        //TODO remove this filter
        IFilter<Face, Face> debugMover = new MovingFilter<>(new Vec4(300, 300, 0, 0));

        IFilter<Model, Face> source = new ModelSource<>();
        IFilter<Face, Face> scaler = new ScalerFilter<>();
        ModelViewTransformation modelViewTrans = new ModelViewTransformation(pd.getModelTranslation(), pd.getViewTransform());
        IFilter<Face, Face> backFaceCuller = new BackfaceCuller();

        //dont know if these are in "correct" place or generified "correctly"
        IFilter<Face, Face> viewPortTrans = new ViewPortTransformation(pd.getViewportTransform());
        IFilter<Face, Face> projectionTrans = new ProjectionTransformation(pd.getProjTransform());

        IFilter<Face, Face> sink = new Renderer<>(pd.getGraphicsContext(), pd.getRenderingMode(), pd.getModelColor());

        chainFilters(source, scaler, modelViewTrans, backFaceCuller, debugMover, sink);

        // TODO 1. perform model-view transformation from model to VIEW SPACE coordinates

        // TODO 2. perform backface culling in VIEW SPACE

        // TODO 3. perform depth sorting in VIEW SPACE

        // lighting can be switched on/off
        if (pd.isPerformLighting()) {
            // 4a. TODO perform lighting in VIEW SPACE
            
            // 5. TODO perform projection transformation on VIEW SPACE coordinates
        } else {
            // 5. TODO perform projection transformation
        }

        // TODO 6. perform perspective division to screen coordinates

        // TODO 7. feed into the sink (renderer)


        // returning an animation renderer which handles clearing of the
        // viewport and computation of the praction
        return new AnimationRenderer(pd) {
            private double rotationPerSecond = 1;
            private double currentRotation = 0;

            /** This method is called for every frame from the JavaFX Animation
             * system (using an AnimationTimer, see AnimationRenderer). 
             * @param fraction the time which has passed since the last render call in a fraction of a second
             * @param model    the model to render 
             */
            @Override
            protected void render(float fraction, Model model) {
                currentRotation += rotationPerSecond * fraction;
                currentRotation = currentRotation % (Math.PI * 2);

                Mat4 rotationMatrix = Matrices.rotate(
                        (float) currentRotation,
                        pd.getModelRotAxis()
                );
                modelViewTrans.setRotationMatrix(rotationMatrix);

                source.write(model);
            }
        };
    }
}