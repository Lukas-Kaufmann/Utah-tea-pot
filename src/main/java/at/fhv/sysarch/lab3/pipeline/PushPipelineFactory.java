package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
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
    public static AnimationTimer createPipeline(PipelineData pd) {
        // TODO: push from the source (model)

        // TODO: the connection of filters and pipes requires a lot of boilerplate code. Think about options how this can be minimized
        IFilter source = new ModelSource<Model>();
        IFilter filter = new ModelFilter();
        IFilter sink = new Renderer(pd.getGraphicsContext(), pd.getRenderingMode());
        IFilter mover = new MovingFilter(new Vec4(300, 100, 0, 0));
        RotationAnimationFilter rotater = new RotationAnimationFilter();

        connectFilters(source, filter);
        connectFilters(filter, rotater);
        connectFilters(rotater, mover);
        connectFilters(mover, sink);

        // TODO 1. perform model-view transformation from model to VIEW SPACE coordinates

        // TODO 2. perform backface culling in VIEW SPACE

        // TODO 3. perform depth sorting in VIEW SPACE

        // TODO 4. add coloring (space unimportant)

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
            private double rotationPerSecond = Math.PI;
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
                // TODO compute rotation in radians

                // TODO create new model rotation matrix using pd.modelRotAxis
                Mat4 rotationMatrix = Matrices.rotate(
                        (float) currentRotation,
                        pd.getModelRotAxis()
                );
                //TODO set this value in the rotating filter
                rotater.setRotationMatrix(rotationMatrix);

                // TODO compute updated model-view tranformation

                // TODO update model-view filter

                // TODO trigger rendering of the pipeline

                // line
                pd.getGraphicsContext().setStroke(Color.PINK);

                source.write(model);
            }
        };
    }
}