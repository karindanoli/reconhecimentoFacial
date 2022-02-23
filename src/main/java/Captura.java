import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.awt.event.KeyEvent;

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

        // janela de preview ao inves de utilizar este codigo de gamma pode ser 1 ou nada
        CanvasFrame cFrame = new CanvasFrame ("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        //Se a camera estiver capturando algo o código vai ser diferente de null e vai executar o codigo abaixo
        while ((frameCapturado = camera.grab()) != null){
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }
            }
        cFrame.dispose();
        camera.stop();
    }
}
