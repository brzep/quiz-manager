package quizmanager.presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import quizmanager.controller.QuizManagerController;
import quizmanager.model.PrizeDto;
import quizmanager.model.PrizeTypeDto;
import quizmanager.model.QuizListElement;
import quizmanager.model.RecordDto;
import quizmanager.service.QuizService;
import rx.Observable;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class QuizView implements Initializable {

    public QuizView(QuizService service){
        this.service = service;
    }


    private QuizManagerController appController;

    // Quiz table

    @FXML
    private TableView<RecordDto> quizDetailsTable;

    @FXML
    private TableColumn<RecordDto, String> nickname;

    @FXML
    private TableColumn<RecordDto, Integer> score;

    @FXML
    private TableColumn<RecordDto, Timestamp> startTimestamp;
    @FXML
    private TableColumn<RecordDto, Timestamp> endTimestamp;



    @FXML
    private TableColumn<RecordDto, String> prize; // TODO change type


    // Quiz title list

    @FXML
    private ListView<String> quizTitles;
    private  QuizService service;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        startTimestamp.setCellValueFactory(new PropertyValueFactory<>("startTimestamp"));
        endTimestamp.setCellValueFactory(new PropertyValueFactory<>("endTimestamp"));
        prize.setCellValueFactory(new PropertyValueFactory<>("prize"));

        // listener working well
        quizTitles.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String selectedQuiz = quizTitles
                            .getSelectionModel()
                            .getSelectedItem();
                    getAndShowSelectedQuiz(selectedQuiz);
                });



        service.loadQuizTitles().subscribe(
                next -> quizTitles.getItems().addAll(next),
                System.out::println,
                () -> {
                    if (!quizTitles.getItems().isEmpty()){
                        quizTitles.getSelectionModel().select(0);
                    }
                }


        );


    }

    private void getAndShowSelectedQuiz(String selectedQuiz) {


        service.loadQuiz(selectedQuiz).subscribe(
                next -> {
                    ObservableList<RecordDto> data = FXCollections.observableArrayList();
                    data.addAll(next);
                    quizDetailsTable.setItems(data);

                },
                System.out::println);


    }


    @FXML
    public void addQuiz() {
        var quizListElement = new QuizListElement();
        if (appController.showFormUploadDialog(quizListElement)) {

            service.uploadQuiz(quizListElement).subscribe(
                    next -> quizTitles.getItems().add(quizListElement.getName()),
                    System.out::println
            );

        }
    }

    @FXML
    public void addPrize() {
        PrizeDto prizeDto = new PrizeDto();

        if(appController.showAddPrizeDialog(prizeDto, service.getPrizeTypes())) {
            service.uploadPrize(prizeDto);
        }
    }


    @FXML
    public void addPrizeType() {
        var prizeTypeDto = new PrizeTypeDto();
        if (appController.showNewPrizeTypeDialog(prizeTypeDto)) {
            // upload prize type
        }
    }


    public void setAppController(QuizManagerController appController) {
        this.appController = appController;
    }

}
