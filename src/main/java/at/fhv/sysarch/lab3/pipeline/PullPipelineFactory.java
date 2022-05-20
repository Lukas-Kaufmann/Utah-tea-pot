package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.filter.DepthSorter;
import at.fhv.sysarch.lab3.pipeline.filter.ModelSource;
import at.fhv.sysarch.lab3.pipeline.filter.Renderer;
import at.fhv.sysarch.lab3.pipeline.tranformers.*;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import javafx.animation.AnimationTimer;

public class PullPipelineFactory {

    public static void connectFilters(IFilter<?, ?> filter1, IFilter<?, ?> filter2) {
        Pipe pipe = new Pipe();
        pipe.setPredecessor(filter1);
        filter2.setPredecessor(pipe);
    }

    /**Assumes the parameters are matching (e.g. filter1 = IFilter<?, O>, filter2 = Filter&lt;O, ?>)
     * as assignments are done with Raw references
     * @param filters
     */
    public static void chainFilters(IFilter<?, ?>... filters) {
        if (filters.length <= 1) {
            return;
        }
        for (int i = 0; i < filters.length - 1; i+=1) {
            connectFilters(filters[i], filters[i+1]);
        }
    }
    public static AnimationTimer createPipeline(PipelineData pd) {

        ModelSource source = new ModelSource();
        ModelViewTransformation modelViewTrans = new ModelViewTransformation(pd.getModelTranslation(), pd.getViewTransform());
        IFilter<Face, Face> modelViewFilter = Filter.ofTransformer(modelViewTrans);
        IFilter<Face, Face> depthSorter = new DepthSorter();
        IFilter<Face, Face> backFaceCuller = Filter.ofTransformer(new BackfaceCuller());

        IFilter<Face, ColoredFace> coloringFilter = Filter.ofTransformer(new ColorTransformer(pd.getModelColor()));
        IFilter<ColoredFace, ColoredFace> viewPortFilter = Filter.ofTransformer(new ViewPortTransformer(pd.getViewportTransform()));
        IFilter<ColoredFace, ColoredFace> projectionFilter = Filter.ofTransformer(new ProjectionTransformer(pd.getProjTransform()));

        IFilter<ColoredFace, ?> sink = new Renderer(pd.getGraphicsContext(), pd.getRenderingMode());

        // TODO 3. perform depth sorting in VIEW SPACE
        // lighting can be switched on/off
        if (pd.isPerformLighting()) {
            IFilter<ColoredFace, ColoredFace> shadingFilter = Filter.ofTransformer(new ShadingTransformer(pd.getLightPos()));
            chainFilters(source, modelViewFilter, backFaceCuller, depthSorter, coloringFilter, shadingFilter, projectionFilter, viewPortFilter, sink);
        } else {
            chainFilters(source, modelViewFilter, backFaceCuller, depthSorter, coloringFilter, projectionFilter, viewPortFilter, sink);
        }

        // returning an animation renderer which handles clearing of the
        // viewport and computation of the praction
        return new AnimationRenderer(pd) {
            private double rotationSpeed = 1;
            private double currentRotation = 0;

            /** This method is called for every frame from the JavaFX Animation
             * system (using an AnimationTimer, see AnimationRenderer). 
             * @param fraction the time which has passed since the last render call in a fraction of a second
             * @param model    the model to render 
             */
            @Override
            protected void render(float fraction, Model model) {
                currentRotation += rotationSpeed * fraction;
                currentRotation = currentRotation % (Math.PI * 2);

                Mat4 rotationMatrix = Matrices.rotate(
                        (float) currentRotation,
                        pd.getModelRotAxis()
                );
                modelViewTrans.setRotationMatrix(rotationMatrix);

                source.setModel(model);
                sink.read();
            }
        };
    }
}