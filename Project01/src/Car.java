import java.io.*;


public class Car {
    private int initSpeed; // m/s
    private int minInitSpeed = 20; // m/s
    private int maxInitSpeed = 80; // m/s
    private int distInter; // m
    private int minDistInter = 10; // m
    private int maxDistInter = 150; // m
    private int negAcc; // m/s^2
    private int minNegAcc = 1; // m/s^2
    private int maxNegAcc = 3; // m/s^2
    private int posAcc; // m/s^2
    private int minPosAcc = 1; // m/s^2
    private int maxPosAcc = 3; // m/s^2
    private int vMax = 100; //m/s
    private int minVMax = 50;
    private int maxVMax = 100;

    public Car(int newInitSpeed, int newDistInter, int newNegAcc, int newPosAcc){
        setInitSpeed(newInitSpeed);
        setDistInter(newDistInter);
        setNegAcc(newNegAcc);
        setPosAcc(newPosAcc);
    }

    public Car(int newInitSpeed, int newDistInter, int newNegAcc, int newPosAcc, int newVMax){
        this(newInitSpeed, newDistInter, newNegAcc, newPosAcc);
        setVMax(newVMax);
    }

    public int getInitSpeed(){
        return initSpeed;
    }

    public int getDistInter(){
        return distInter;
    }

    public int getNegAcc(){return negAcc;}

    public int getPosAcc(){return posAcc;}

    public int getVMax(){return vMax;}

    public void setInitSpeed(int newInitSpeed){
        if(newInitSpeed >= minInitSpeed & newInitSpeed <= maxInitSpeed & newInitSpeed <= vMax){
            initSpeed = newInitSpeed;
        }
        else if(newInitSpeed > maxInitSpeed | newInitSpeed > vMax){
            if(maxInitSpeed >= vMax){
                initSpeed = vMax;
            }
            else{
                initSpeed = maxInitSpeed;
            }
        }
        else{
            initSpeed = minInitSpeed;
        }
    }

    public void setDistInter(int newDistInter){
        if(newDistInter >= minDistInter & newDistInter <= maxDistInter){
            distInter = newDistInter;
        }
        else if(newDistInter > maxDistInter){
            distInter = maxDistInter;
        }
        else{
            distInter = minDistInter;
        }
    }

    public void setNegAcc(int newNegAcc){
        if(newNegAcc >= minNegAcc & newNegAcc <= maxNegAcc){
            negAcc = newNegAcc;
        }
        else if(newNegAcc > maxNegAcc){
            negAcc = maxNegAcc;
        }
        else{
            negAcc = minNegAcc;
        }
    }

    public void setPosAcc(int newPosAcc){
        if(newPosAcc >= minPosAcc & newPosAcc <= maxPosAcc){
            posAcc = newPosAcc;
        }
        else if(newPosAcc > maxPosAcc){
            posAcc = maxPosAcc;
        }
        else{
            posAcc = minPosAcc;
        }
    }

    public void setVMax(int newVMax){
        if(newVMax >= minVMax & newVMax <= maxVMax){
            vMax = newVMax;
        }
        else if(newVMax > maxVMax){
            vMax = maxVMax;
        }
        else{
            vMax = minVMax;
        }
    }

    public void printStats(){
        System.out.println("Initial speed of the car: " + initSpeed + " m/s");
        System.out.println("Distance from intersection: " + distInter + " m");
        System.out.println("Deceleration: " + negAcc + " m/s^2");
        System.out.println("Acceleration: " + posAcc + " m/s^2");
    }

    public Quadruple getThrough(Intersection inter){
        int dist = distInter + inter.getWidth();
        int t = inter.getYellowDur();
        Quadruple result = new Quadruple();
        result.distOnPos = initSpeed * t + 0.5 * posAcc * t * t;
        result.distOnNeg = initSpeed * t - 0.5 * negAcc * t * t;
        result.pass = false;
        result.stop = false;
        if(dist <= result.distOnPos){
            result.pass = true;
        }
        if(distInter >= result.distOnNeg){
            result.stop = true;
        }
        inter.printStats();
        printStats();
        System.out.println("Minimum distance needed for car to stop: " + result.distOnNeg);
        System.out.println("Maximum distance car will manage to pass: " + result.distOnPos);
        if(result.pass == true){
            System.out.println("THE CAR WILL MANAGE TO PASS THROUGH THE INTERSECTION");
        }
        if(result.stop == true){
            System.out.println("THE CAR WILL MANAGE TO STOP BEFORE THE INTERSECTION");
        }
        if(result.pass == false & result.stop == false){
            System.out.println("THE CAR WILL NOT MANAGE TO DO ANYTHING");
        }
        System.out.println("=============================================================");
        return result;
    }

    public Quadruple getThroughEx5(Intersection inter){
        int dist = distInter + inter.getWidth();
        int t = inter.getYellowDur();

        Quadruple result = new Quadruple();

        result.pass = false;
        result.stop = false;

        double maxT = (vMax - initSpeed) / posAcc;
        result.distOnPos = 0;
        if(maxT >= t){
            result.distOnPos = initSpeed * t + 0.5 * posAcc * t * t;
        }
        else{
            result.distOnPos = initSpeed * maxT + 0.5 * posAcc * maxT * maxT;
            double remT = t - maxT;
            result.distOnPos += vMax * remT;
        }

        result.distOnNeg = initSpeed * t - 0.5 * negAcc * t * t;

        if(dist <= result.distOnPos){
            result.pass = true;
        }
        if(distInter >= result.distOnNeg){
            result.stop = true;
        }

        if(result.pass == true){
            System.out.println("THE CAR WILL MANAGE TO PASS THROUGH THE INTERSECTION");
        }
        if(result.stop == true){
            System.out.println("THE CAR WILL MANAGE TO STOP BEFORE THE INTERSECTION");
        }
        if(result.pass == false & result.stop == false){
            System.out.println("THE CAR WILL NOT MANAGE TO DO ANYTHING");
        }
        System.out.println("=============================================================");
        return result;
    }

    public void frontCar(Car otherCar, Intersection inter){
        if(otherCar.getDistInter() > distInter){
            Quadruple frontResult = getThrough(inter);
            Quadruple backResult = otherCar.getThrough(inter);
            if(frontResult.pass == true){
                System.out.println("FIRST CAR SHALL PASS");
                if(backResult.pass == true){
                    if(frontResult.distOnPos - getDistInter() - inter.getWidth() > backResult.distOnPos - otherCar.getDistInter() - inter.getWidth()){
                        System.out.println("SECOND CAR SHALL PASS");
                    }
                    else if(backResult.stop == true){
                        System.out.println("SECOND CAR SHALL STOP");
                    }
                    else{
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
                else{
                    if (backResult.stop == true) {
                        System.out.println("SECOND CAR SHALL STOP");
                    } else {
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
            }
            else if(frontResult.pass == false & frontResult.stop == true){
                if(getDistInter() - frontResult.distOnNeg < otherCar.getDistInter() - backResult.distOnNeg){
                    System.out.println("FIRST CAR SHALL STOP");
                    System.out.println("SECOND CAR SHALL STOP");
                }
                else if(backResult.stop == false){
                    System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    System.out.println("SECOND CAR SHALL STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                }
            }
            else{
                if(backResult.stop == true){
                    System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    System.out.println("SECOND CAR SHALL STOP");
                }
                else{
                    if(frontResult.distOnPos - getDistInter() <= backResult.distOnNeg - otherCar.getDistInter()){
                        System.out.println("COLLISION IS UNAVOIDABLE");
                    }
                    else if(frontResult.distOnPos - getDistInter() > backResult.distOnNeg - otherCar.getDistInter()){
                        System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                    else if(frontResult.distOnPos - getDistInter() > backResult.distOnPos - otherCar.getDistInter()){
                        System.out.println("FIRST CAR SHALL PASS, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                        System.out.println("SECOND CAR SHOULD PASS, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
            }
        }
        else{
            Quadruple frontResult = otherCar.getThrough(inter);
            Quadruple backResult = getThrough(inter);
            if(frontResult.pass == true){
                System.out.println("FIRST CAR SHALL PASS");
                if(backResult.pass == true){
                    if(frontResult.distOnPos - otherCar.getDistInter() - inter.getWidth() > backResult.distOnPos - getDistInter() - inter.getWidth()){
                        System.out.println("SECOND CAR SHALL PASS");
                    }
                    else if(backResult.stop == true){
                        System.out.println("SECOND CAR SHALL STOP");
                    }
                    else{
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
                else{
                    if (backResult.stop == true) {
                        System.out.println("SECOND CAR SHALL STOP");
                    } else {
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
            }
            else if(frontResult.pass == false & frontResult.stop == true){
                if(otherCar.getDistInter() - frontResult.distOnNeg < getDistInter() - backResult.distOnNeg){
                    System.out.println("FIRST CAR SHALL STOP");
                    System.out.println("SECOND CAR SHALL STOP");
                }
                else if(backResult.stop == false){
                    System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    System.out.println("SECOND CAR SHALL STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                }
            }
            else{
                if(backResult.stop == true){
                    System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    System.out.println("SECOND CAR SHALL STOP");
                }
                else{
                    if(frontResult.distOnPos - otherCar.getDistInter() <= backResult.distOnNeg - getDistInter()){
                        System.out.println("COLLISION IS UNAVOIDABLE");
                    }
                    else if(frontResult.distOnPos - otherCar.getDistInter() > backResult.distOnNeg - getDistInter()){
                        System.out.println("FIRST CAR SHOULD PASS TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                        System.out.println("SECOND CAR SHOULD STOP TO AVOID COLLISION, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                    else if(frontResult.distOnPos - otherCar.getDistInter() > backResult.distOnPos - getDistInter()){
                        System.out.println("FIRST CAR SHALL PASS, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                        System.out.println("SECOND CAR SHOULD PASS, HOWEVER IT WILL NOT MANAGE TO PASS THE WHOLE INTERSECTION");
                    }
                }
            }
        }
    }
}
