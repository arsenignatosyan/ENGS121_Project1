public class Test {
    public static void main(String args[]){
//        Intersection inter = new Intersection(5, 2);
//        Car car = new Car(20, 40, 3, 3);
//        car.getThrough(inter);
//
//        inter = new Intersection(12, 4);
//        car = new Car(80, 100, 2, 2);
//        car.getThrough(inter);
//
//        inter = new Intersection(5, 5);
//        car = new Car(80, 40, 2, 1);
//        car.getThrough(inter);
//
//        inter = new Intersection(12, 2);
//        car = new Car(60, 130, 3, 3);
//        car.getThrough(inter);
//
//        inter = new Intersection(12, 4);
//        car = new Car(60, 10, 2, 3);
//        car.getThrough(inter);
//
//        inter = new Intersection(19, 4);
//        car = new Car(20, 70, 1, 1);
//        car.getThrough(inter);
//
//        inter = new Intersection(19, 2);
//        car = new Car(40, 70, 3, 1);
//        car.getThrough(inter);
//
//        inter = new Intersection(12, 3);
//        car = new Car(40, 130, 2, 3);
//        car.getThrough(inter);

//        Intersection inter = new Intersection(12, 4);
//        Car car = new Car(30, 150, 2, 3, 50);
//        car.getThroughEx5(inter);

        Intersection inter = new Intersection(5, 2);
        Car car = new Car(20, 40, 3, 3);
        Car otherCar = new Car(80, 100, 2, 2);
        car.frontCar(otherCar, inter);
    }
}
