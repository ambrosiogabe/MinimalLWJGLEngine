package eventHandlers;

import jade.Window;

import static org.lwjgl.opengl.GL11.glViewport;

public class WindowResizeListener {

    public static void resizeCallback(long glfwWindow, int screenWidth, int screenHeight) {
        Window.get().setWidth(screenWidth);
        Window.get().setHeight(screenHeight);

        // Figure out the largest area that fits this target aspect ratio
        int aspectWidth = screenWidth;
        int aspectHeight = (int)((float)aspectWidth / Window.get().getTargetAspectRatio());
        if (aspectHeight > screenHeight) {
            // It doesn't fit our height, we must switch to pillarbox
            aspectHeight = screenHeight;
            aspectWidth = (int)((float)aspectHeight * Window.get().getTargetAspectRatio());
        }

        // Center rectangle
        int vpX = (int)(((float)screenWidth / 2f) - ((float)aspectWidth / 2f));
        int vpY = (int)(((float)screenHeight / 2f) - ((float)aspectHeight / 2f));

        glViewport(vpX, vpY, aspectWidth, aspectHeight);
    }
}
