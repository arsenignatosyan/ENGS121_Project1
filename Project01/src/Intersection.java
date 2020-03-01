public class Intersection {
    private int width; // m
    private int minWidth = 5; // m
    private int maxWidth = 20; // m
    private int yellowDur; // s
    private int minYellowDur = 2; // s
    private int maxYellowDur = 5; // s

    public Intersection(int newWidth, int newYellowDur){
        setWidth(newWidth);
        setYellowDur(newYellowDur);
    }

    public int getWidth(){
        return width;
    }

    public int getYellowDur(){
        return yellowDur;
    }

    public void setWidth(int newWidth){
        if(newWidth >= minWidth & newWidth <= maxWidth) {
            width = newWidth;
        }
        else if(newWidth > maxWidth){
            width = maxWidth;
        }
        else{
            width = minWidth;
        }
    }

    public void setYellowDur(int newYellowDur){
        if(newYellowDur >= minYellowDur & newYellowDur <= maxYellowDur) {
            yellowDur = newYellowDur;
        }
        else if(newYellowDur > maxYellowDur){
            yellowDur = maxYellowDur;
        }
        else{
            yellowDur = minYellowDur;
        }
    }

    public void printStats(){
        System.out.println("Width of the intersection: " + width + " m");
        System.out.println("Duration of yellow light: " + yellowDur + " s");
    }

}
