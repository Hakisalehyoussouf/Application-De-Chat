package ma.ensate.client.controllers;
/*
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileContactController implements Initializable{
    
    @FXML
    private Button btnChat;

    @FXML
    private Button btnGroup;

    @FXML
    private Button btnLogout;
    
    @FXML
    private Button closebtn;

    @FXML
    private Button btnProfileManaging;

    @FXML
    private Button btnSettings;

    @FXML
    private AnchorPane main_form;
    
    
    @FXML
    private ListView<String> contactList;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField ville;

    @FXML
    private TextField search;


  
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadcontacts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        contactList.setFixedCellSize(50.0); }

        @FXML
        protected void searchcontact() throws IOException {

        String search_text = search.getText().strip().toLowerCase();
        loadcontacts();
        if (search_text.length() >= 3) {
            ArrayList<String> results = new ArrayList<>();

            for(String contact: contactList.getItems()) {
                if (contact.toLowerCase().contains(search_text)) results.add(contact);
            }

            contactList.getItems().clear();
            if (results.size() > 0) {
                for(String found_contact: results) contactList.getItems().add(found_contact);
                contactList.refresh();
            }
        }
    }
    
    Stage stage;
    @FXML
    void handleButtonAction(ActionEvent event) {
                       
        if(event.getSource()==closebtn){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       @FXML
    protected void editcontact() throws IOException {
        ObservableList<Integer> selectedIndices = contactList.getSelectionModel().getSelectedIndices();

        if (selectedIndices.size() == 1) {
            String contactToEdit = contactList.getItems().get(selectedIndices.get(0));
            String oldIsbn = contactToEdit.split(";")[2];


            Editcontact eb = new Editcontact();
            String str = eb.getResult();

            if (str != null) {
                Path p = Paths.get("src/data/" + oldIsbn + ".txt");

                File fileToDelete = new File(p.toString());
                fileToDelete.delete();



                Path p2 = Paths.get("src/data/" + str.split(";")[2] + ".txt");
                File newFile = new File(p2.toString());

                if(newFile.createNewFile()) {
                    FileWriter myWriter = new FileWriter(String.valueOf(p2));
                    myWriter.write(str);
                    myWriter.close();
                }

                loadcontacts();
                search.setText("");
            }
        }
    }
    
    
    @FXML
    protected void deletecontact() throws IOException {
        ObservableList<Integer> selectedIndices = contactList.getSelectionModel().getSelectedIndices();

        if (selectedIndices.size() == 1) {
            String contactToEdit = contactList.getItems().get(selectedIndices.get(0));
            String oldIsbn = contactToEdit.split(";")[2];
            Path p = Paths.get("src/data/" + oldIsbn + ".txt");
            File fileToDelete = new File(p.toString());
            fileToDelete.delete();

            loadcontacts();
            search.setText("");
        }
    }

    @FXML
    protected void addItem() throws IOException {

        String nom_text = nom.getText();
        String prenom_text = prenom.getText();
        String email_text = email.getText();
        String ville_text = ville.getText();

        StringBuilder sb = new StringBuilder();
        sb.append(nom_text);
        sb.append(";");
        sb.append(prenom_text);
        sb.append(";");
        sb.append(email_text);
        sb.append(";");
        sb.append(ville_text);

        String data = new String(sb);
        Path p = Paths.get("src/data/" + nom_text + ".txt");
        File myObj = new File(String.valueOf(p));
        if(myObj.createNewFile()) {
            FileWriter myWriter = new FileWriter(String.valueOf(p));
            myWriter.write(data);
            myWriter.close();
        }

        nom.setText("");
        prenom.setText("");
        email.setText("");
        ville.setText("");

        this.loadcontacts();
    }

    public static ArrayList<String> listFilesForFolder(final File folder) throws IOException {
        ArrayList<String> al = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            String read = Files.readAllLines(Paths.get(fileEntry.getPath())).get(0);
            al.add(read.strip());
        }
        return al;
    }

    public void loadcontacts() throws IOException {
        Path p = Paths.get("src/data");
        final File folder = new File(String.valueOf(p));
        ArrayList<String> al = listFilesForFolder(folder);
        contactList.getItems().clear();
        for(String contact: al) {
            contactList.getItems().add(contact);
        }
        contactList.refresh();
    }


}
*/