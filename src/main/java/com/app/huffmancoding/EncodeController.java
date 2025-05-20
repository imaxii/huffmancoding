package com.app.huffmancoding;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;

public class EncodeController {

    private static final int NODE_RADIUS = 20;
    private static final int VERTICAL_SPACING = 80;

    @FXML
    private Canvas canvas;
    @FXML
    private MFXTextField inputField;
    @FXML
    private HBox hbox;
    @FXML
    private TextArea bitString;
    @FXML
    private TableView<CharacterCode> tableView;
    @FXML
    private TableColumn<CharacterCode, Character> characterColumn;
    @FXML
    private TableColumn<CharacterCode, String> codeColumn;
    private double scaleFactor = 1.0;
    private final Scale scale = new Scale(1, 1, 0, 0);
    @FXML
    private Group group;
    private HuffmanCoding huffmanCoding;

    private double scaleValue = 1.0;
    private final double zoomIntensity = 0.02;
    private final double minScale = 0.1;
    private final double maxScale = 10.0;

    @FXML
    public void initialize() {

        characterColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().characterProperty();
        });
        codeColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().codeProperty();
        });

        group.getTransforms().add(scale);
        // Dibujar en el canvas
        double canvasWidth = canvas.getWidth();
        double centerX = canvasWidth / 2;
        // Obtener el contexto gráfico y traducir horizontalmente para centrar futuros dibujos
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.translate(centerX / 2, 0);
        // Manejar el zoom con la rueda del mouse
        group.setOnScroll((ScrollEvent event) -> {
            double delta = event.getDeltaY();
            if (delta > 0) {
                scaleFactor *= 1.1;  // Zoom in
            } else {
                scaleFactor /= 1.1;  // Zoom out
            }
            scale.setX(scaleFactor);
            scale.setY(scaleFactor);
            event.consume();
        });
        this.huffmanCoding = new HuffmanCoding();

        setupZooming();

        hbox.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double width = newWidth.doubleValue();
            //region30.setPrefWidth(width * 0.3);
            inputField.setPrefWidth(width * 0.85);
        });
    }

    private void drawTree(GraphicsContext gc, Node node, double x, double y, double horizontalOffset) {
        if (node == null) {
            return;
        }
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        String text = node.isLeafNode() ? String.valueOf(node.getCharacter() + "|" + node.getFrequency()) : String.valueOf(node.getFrequency());
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText(text, x - 10, y + 5);

        if (node.getLeft() != null) {
            double childX = x - horizontalOffset * 1.3;
            double childY = y + VERTICAL_SPACING;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            gc.fillText("0", (x + childX) / 2, (y + childY - 10) / 2); // Etiqueta para la arista izquierda
            drawTree(gc, node.getLeft(), childX, childY, horizontalOffset / 2);
        }

        if (node.getRight() != null) {
            double childX = x + horizontalOffset * 1.3;
            double childY = y + VERTICAL_SPACING;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            gc.fillText("1", (x + childX) / 2, (y + childY - 10) / 2); // Etiqueta para la arista derecha
            drawTree(gc, node.getRight(), childX, childY, horizontalOffset / 2);
        }
    }

    public void encode(MouseEvent mouseEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Result result = huffmanCoding.encode(inputField.getText().toCharArray());
        drawTree(gc, result.getTree(), 250, 50, 200);
        bitString.setText(result.getBitString());
        // Limpiar la tabla
        tableView.getItems().clear();
        // Agregar todos los elementos de la lista a la tabla
        result.getTable().forEach((key, value) -> tableView.getItems().add(new CharacterCode(key, value)));
    }

    private void setupZooming() {
        group.setOnScroll((ScrollEvent event) -> {
            if (event.getDeltaY() == 0) return;

            double zoomFactor = (event.getDeltaY() > 0) ? (1 + zoomIntensity) : (1 - zoomIntensity);
            double newScale = scaleValue * zoomFactor;

            // Limitar escala
            if (newScale < minScale || newScale > maxScale) return;

            scaleValue = newScale;

            double x = event.getX();
            double y = event.getY();

            // Limpiar transformaciones previas
            group.getTransforms().clear();

            // Aplicar nueva transformación de escala relativa al puntero
            Scale scale = new Scale(scaleValue, scaleValue, x, y);
            group.getTransforms().add(scale);

            event.consume();
        });
    }
}
