package ph.edu.dlsu.lbycpob.hellojavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PhilippineFlagApp extends Application {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 300;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Blue stripe (top half)
        Rectangle blueStripe = new Rectangle(0, 0, WIDTH, HEIGHT / 2);
        blueStripe.setFill(Color.web("#0038A8"));

        // Red stripe (bottom half)
        Rectangle redStripe = new Rectangle(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
        redStripe.setFill(Color.web("#CE1126"));

        // White equilateral triangle (left side)
        double triangleWidth = HEIGHT * (Math.sqrt(3) / 2); // roughly equilateral proportion
        Polygon triangle = new Polygon(
                0, 0,
                0, HEIGHT,
                triangleWidth, HEIGHT / 2
        );
        triangle.setFill(Color.WHITE);

        // Sun (centered in triangle, offset left)
        double sunX = triangleWidth * 0.35;
        double sunY = HEIGHT / 2;
        double sunRadius = 25;
        Circle sun = new Circle(sunX, sunY, sunRadius);
        sun.setFill(Color.web("#FCD116"));

        // 8 sun rays
        Pane rays = new Pane();
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            double innerR = sunRadius + 5;
            double outerR = sunRadius + 25;
            Line ray = new Line(
                    sunX + innerR * Math.cos(angle), sunY + innerR * Math.sin(angle),
                    sunX + outerR * Math.cos(angle), sunY + outerR * Math.sin(angle)
            );
            ray.setStroke(Color.web("#FCD116"));
            ray.setStrokeWidth(3);
            rays.getChildren().add(ray);
        }

        // 3 stars at triangle corners (simplified as small circles — replace with Polygon star if required)
        double starRadius = 8;
        Circle star1 = new Circle(20, 20, starRadius, Color.web("#FCD116"));
        Circle star2 = new Circle(20, HEIGHT - 20, starRadius, Color.web("#FCD116"));
        Circle star3 = new Circle(triangleWidth - 20, HEIGHT / 2, starRadius, Color.web("#FCD116"));

        root.getChildren().addAll(blueStripe, redStripe, triangle, sun, rays, star1, star2, star3);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Philippine Flag - PhilippineFlagApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Pane createSunRays(double centerX, double centerY, double sunRadius, double rayLength) {
        Pane rays = new Pane();
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            double startX = centerX + sunRadius * Math.cos(angle); // starts ON the circle
            double startY = centerY + sunRadius * Math.sin(angle);
            double endX = centerX + (sunRadius + rayLength) * Math.cos(angle);
            double endY = centerY + (sunRadius + rayLength) * Math.sin(angle);

            Line ray = new Line(startX, startY, endX, endY);
            ray.setStroke(Color.web("#FCD116"));
            ray.setStrokeWidth(3);
            rays.getChildren().add(ray);
        }
        return rays;
    }

    private Polygon createStar(double centerX, double centerY, double outerRadius) {
        double innerRadius = outerRadius * 0.5;
        double[] points = new double[20]; // 10 vertices × (x, y)

        for (int i = 0; i < 10; i++) {
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double angle = Math.toRadians(-90 + i * 36);
            points[i * 2] = centerX + radius * Math.cos(angle);
            points[i * 2 + 1] = centerY + radius * Math.sin(angle);
        }

        Polygon star = new Polygon(points);
        star.setFill(Color.web("#FCD116"));
        return star;
    }
}