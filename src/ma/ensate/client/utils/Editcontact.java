package java;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class Editcontact {

    private String result;

    public String getResult() {
        return this.result;
    }

    public Editcontact() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit contact");
        dialog.setHeaderText("Edit contact");

        ButtonType confirm = new ButtonType("Edit");
        dialog.getDialogPane().getButtonTypes().add(confirm);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField nom = new TextField();
        nom.setPromptText("nom");

        TextField prenom = new TextField();
        prenom.setPromptText("prenom");

        TextField email = new TextField();
        email.setPromptText("email");

        TextField ville = new TextField();
        ville.setPromptText("ville");

        grid.add(new Label("nom:"), 0, 0);
        grid.add(nom, 1, 0);

        grid.add(new Label("prenom:"), 0, 1);
        grid.add(prenom, 1, 1);

        grid.add(new Label("email:"), 0, 2);
        grid.add(email, 1, 2);

        grid.add(new Label("ville:"), 0, 3);
        grid.add(ville, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                return nom.getText() + ";" + prenom.getText() + ";" + email.getText() + ";" + ville.getText();
            }
            return null;
        });

        Optional<String> rslt = dialog.showAndWait();
        if (rslt.isPresent() ) {
            this.result = rslt.get();
        }
        else this.result = null;
    }
}
