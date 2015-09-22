package suskun.nn;

import java.io.File;
import java.io.IOException;

/**
 * This is an optimized feed-forward neural network implementation that uses native code.
 * Optimizations are based on
 * - Vanhoucke et al's "Improving the speed of neural networks on CPUs" [2011]
 * <p>
 * Idea is to linearly quantize the weight and activation values to 8 bit values and use special SIMD instructions
 * that uses 8 bit arguments. This not only allows more parameters to calculate in parallel with SIMD instructions
 * but also improves memory throughput greatly.
 * However, input layer weights are not quantized and bias values are quantized to 32 bits.
 * Quantization is applied to each layer separately by taking the maximum weight magnitude and quantize to [-128, 127]
 * Bias values are not quantized.
 * <p>
 * Batch processing. Instead of calculating one input vector at a time, multiple vectors are calculated.
 * <p>
 * Lazy processing (Not yet implemented): In the last layer, not all outputs are required to be calculated.
 * So, only required outputs are calculated. This requires communication with the call side.
 * <p>
 */
public class QuantizedDnn {

    static {
        try {
            NativeUtils.loadLibraryFromJar("/resources/libfast-dnn.so");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    int inputDimension;
    int outputDimension;

    // generates the dnn network in native code from binary network file.
    native void initialize(String fileName);

    public static QuantizedDnn loadFromFile(File dnnFile) {
        QuantizedDnn dnn = new QuantizedDnn();
        dnn.initialize(dnnFile.getAbsolutePath());
        dnn.inputDimension = dnn.inputDimension();
        dnn.outputDimension = dnn.outputDimension();
        return dnn;
    }

    // TODO: not implemented yet.
    native long getContext(int inputVectorCount, int inputDimension, int batchSize);
    // TODO: not implemented yet.
    native void calculateUntilOutput(long contextHandle, float[] input);
    // TODO: not implemented yet.
    native float[] calculateForOutputs(long contextHandle, int inputIndex, int[] outputIndexes);

    native float[] calculate(float[] input, int inputVectorCount, int inputDimension, int batchSize);

    public native int inputDimension();

    public native int outputDimension();

    public float[][] calculate(float[][] input) {
        int dimension = input[0].length;
        float[] flattened = toMatrix(input);
        float[] res1d = calculate(flattened, input.length, dimension, 8);
        return toVector(res1d, input.length, outputDimension);
    }

    private float[] toMatrix(float[][] arr2d) {
        int vecCount = arr2d.length;
        int dimension = arr2d[0].length;
        float[] res = new float[vecCount * dimension];
        for (int i = 0; i < vecCount; i++) {
            System.arraycopy(arr2d[i], 0, res, i * dimension, dimension);
        }
        return res;
    }

    private float[][] toVector(float[] arr, int vectorSize, int dimension) {
        float[][] res = new float[vectorSize][dimension];
        for (int i = 0; i < vectorSize; i++) {
            System.arraycopy(arr, i * dimension, res[i], 0, dimension);
        }
        return res;
    }

}