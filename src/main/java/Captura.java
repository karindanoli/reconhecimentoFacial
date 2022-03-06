import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;



import java.awt.event.KeyEvent;
import java.io.IOException;


public class Captura {
    public static void main(String arg[]) throws IOException, InterruptedException {
        try {
            Loader.load(opencv_core.class);
        } catch (UnsatisfiedLinkError e) {
            String path = Loader.cacheResource(opencv_core.class, "windows-x86_64/jniopencv_core.dll").getPath();
            new ProcessBuilder("C:/Users/PC/Desktop/projetos/dependenciesGUI", path).start().waitFor();
        }
        //eventos do teclado
        KeyEvent tecla = null;
        //converte a imagem para o formato de matriz
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        //pegar a imagem da web cam com o número do dispositivo como é um notebook tem 1 camera só então é zero. Se tiver mais a segunda camera é 1
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        //para tirar a foto
        camera.start();

        //LINHA 1

        // janela de preview ao inves de utilizar este codigo de gamma pode ser 1 ou nada
        CanvasFrame cFrame = new CanvasFrame ("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        //por meio desta imagemColorida que irá ser feito a deteccao do frame


        //Se a camera estiver capturando algo o código vai ser diferente de null e vai executar o codigo abaixo
        while ((frameCapturado = camera.grab()) != null){
            if (cFrame.isVisible()){
                cFrame.showImage(frameCapturado);

                }
            }
        cFrame.dispose();
        camera.stop();
    }
}
