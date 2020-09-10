package week1;

import edu.duke.*;

import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        int count = 0;

        for (Point p : s.getPoints()) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int numPoints = getNumPoints(s);
        double perimeter = getPerimeter(s);

        return perimeter / numPoints;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point lastPoint = s.getLastPoint();
        double prevDistance = 0;
        double currDistance = 0;

        for (Point currPt : s.getPoints()) {
            prevDistance = lastPoint.distance(currPt);
            lastPoint = currPt;
            currDistance = prevDistance;
            if (prevDistance > currDistance) {
                return prevDistance;
            }
        }
        return currDistance;
    }

    public double getLargestX(Shape s) {
        // Put code here

        Point lastPoint = s.getLastPoint();
        int prevValue = lastPoint.getX();
        int currValue = 0;

        for (Point p : s.getPoints()) {
            currValue = p.getX();

            if (currValue > prevValue) {
                return currValue;
            }
            prevValue = currValue;
        }
        return prevValue;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double prevPerimeter = 0;

        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            FileResource fr = new FileResource(file);

            Shape shape = new Shape(fr);
            double perimeter = getPerimeter(shape);

            if (prevPerimeter > perimeter) {
                return prevPerimeter;
            }

            prevPerimeter = perimeter;
        }
        return prevPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        double prevPerimeter = 0;

        try {

            DirectoryResource directoryResource = new DirectoryResource();
            for (File file : directoryResource.selectedFiles()) {
                FileResource fr = new FileResource(file);

                Shape shape = new Shape(fr);
                double perimeter = getPerimeter(shape);

                if (perimeter > prevPerimeter) {
                    temp = file;
                }

                prevPerimeter = perimeter;
            }
        }catch (NullPointerException ex){
            ex.getMessage();
        }

        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);

        double averageLength = getAverageLength(s);
        System.out.println("average length = " + averageLength);

        double largestSide = getLargestSide(s);
        System.out.println(" the largest side = " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("the largest X = " + largestX);
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("The Largest Perimeter Multiple Files = " + largestPerimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println(" File with the Largest Perimeter = " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
