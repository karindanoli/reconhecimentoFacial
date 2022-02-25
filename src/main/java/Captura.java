import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.event.KeyEvent;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.COLOR_BGRA2GRAY;
import static org.opencv.imgproc.Imgproc.rectangle;

public class Captura {
    public static void main(String arg[]) throws FrameGrabber.Exception {
        //eventos do teclado
        KeyEvent tecla = null;
        //converte a imagem para o formato de matriz
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        //pegar a imagem da web cam com o número do dispositivo como é um notebook tem 1 camera só então é zero. Se tiver mais a segunda camera é 1
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        //para tirar a foto
        camera.start();

        //inserindo o arquivo que vai fazer a deteccao de faces
        CascadeClassifier detectorFace = new CascadeClassifier("src\\resoueces\\haarcascade_frontalface_alt.xml");

        // janela de preview ao inves de utilizar este codigo de gamma pode ser 1 ou nada
        CanvasFrame cFrame = new CanvasFrame ("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        //por meio desta imagemColorida que irá ser feito a deteccao do frame
        Mat imagemColorida = new Mat();

        //Se a camera estiver capturando algo o código vai ser diferente de null e vai executar o codigo abaixo
        while ((frameCapturado = camera.grab()) != null){
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            //passar a imagem para a escala de cinza para o programa trabalhar melhor
            cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
            //armazena as faces detectadas nesta variavel
            RectVector facesDetectadas = new RectVector();
            //Passa a foto para faces detectadas e os outros são parametros de tamanho da face que pode ser detectada minimo e maximo
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(150,150), new Size(500,500));
            for (int i=0; i< facesDetectadas.size(); i++){
                //fazer um retangulo por isso o rect
                Rect dadosFace = facesDetectadas.get(0);
                rectangle(imagemColorida, dadosFace, new Scalar(0,0,255,0));
            }
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }
            }
        cFrame.dispose();
        camera.stop();
    }
}
