package songs.guitar.kd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import songs.guitar.kd.model.Competition;
import songs.guitar.kd.model.CompetitionTableRow;
import songs.guitar.kd.model.Competitor;
import songs.guitar.kd.model.CompetitorTableRow;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
https://www.mkyong.com/java/jaxb-hello-world-example/
http://code.makery.ch/library/javafx-8-tutorial/part2/
http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
http://www.java2s.com/Tutorials/Java/JavaFX/0650__JavaFX_TableView.htm
https://examples.javacodegeeks.com/desktop-java/javafx/tableview/javafx-tableview-example/
 */

public class Controller implements Initializable {

    private List<Competitor> competitorsList = new ArrayList<>();

    // ObservableList powoduje, że tabela automatycznie reaguje na zawartości listy tego typu
    private ObservableList<CompetitorTableRow> competitorsTableObservableList = FXCollections.observableArrayList();
    private ObservableList<CompetitionTableRow> competitionsTableObservableList = FXCollections.observableArrayList();

    @FXML
    TableView<CompetitorTableRow> tableCompetitors;

    @FXML
    TableView<CompetitionTableRow> tableCompetitions;

    @FXML
    TableColumn<CompetitorTableRow, Integer> rankedColumn;

    @FXML
    TableColumn<CompetitorTableRow, String> competitorColumn;

    @FXML
    TableColumn<CompetitorTableRow, Integer> amountCompetitionsColumn;

    @FXML
    TableColumn<CompetitorTableRow, Integer> scoreColumn;

    @FXML
    TableColumn<CompetitionTableRow, String> competitionNameColumn;

    @FXML
    TableColumn<CompetitionTableRow, Integer> scoreInCompetition;

    @FXML
    TextField competitorNameField, competitionNameField, scoreInCompetitionField;

    @FXML
    Button addCompetitorButton, addCompetitionButton;

    /*
        The initialize() method is automatically called after the fxml file has been loaded.
         At this time, all the FXML fields should have been initialized already.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createData();
        setTables();
    }

    private void createData() {
        Competitor competitor1 = new Competitor("Geralt z Rivii");
        Competitor competitor2 = new Competitor("Kamil Działek");
        Competitor competitor3 = new Competitor("Adolf Hitler");

        competitor1.addCompetition(new Competition("Konkurs 1", 66));
        competitor1.addCompetition(new Competition("Konkurs 2", 40));
        competitor1.addCompetition(new Competition("Konkurs 3", 30));

        competitor2.addCompetition(new Competition("Konkurs 1", 20));
        competitor2.addCompetition(new Competition("Konkurs 2", 15));
        competitor2.addCompetition(new Competition("Konkurs 3", 10));
        competitor2.addCompetition(new Competition("Konkurs 4", 27));

        competitor3.addCompetition(new Competition("Konkurs 1", 30));
        competitor3.addCompetition(new Competition("Konkurs 2", 8));

        competitorsList.add(competitor1);
        competitorsList.add(competitor2);
        competitorsList.add(competitor3);
    }

    private void setTables() {
        tableCompetitors.setItems(competitorsTableObservableList);
        tableCompetitions.setItems(competitionsTableObservableList);

        createListeners();

        setCellFactoryForEachColumn();

        for (Competitor competitor : competitorsList) {
            competitorsTableObservableList.add(new CompetitorTableRow(competitor));
        }

    }

    private void createListeners() {

        addCompetitorButton.setOnAction((ActionEvent e) -> {
            addCompetitor();
        });

        addCompetitionButton.setOnAction((ActionEvent e) -> {
            addCompetition();
        });

        tableCompetitors.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    refreshCompetitionsTable();

                });
    }

    private void refreshCompetitionsTable() {

        competitionsTableObservableList.clear();

        for(Competition competition : tableCompetitors.getSelectionModel()
                .getSelectedItem().getCompetitorObject().getCompetitions()){

            competitionsTableObservableList.add(new CompetitionTableRow(competition));
        }
    }

    private void setCellFactoryForEachColumn() {

        rankedColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty().asObject());
        competitorColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        amountCompetitionsColumn.setCellValueFactory(cellData -> cellData.getValue().amountCompetitionsProperty().asObject());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        competitionNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        scoreInCompetition.setCellValueFactory(cellData -> cellData.getValue().pointsProperty().asObject());
    }

    private void addCompetitor() {
        Competitor competitor = new Competitor(competitorNameField.getText());

        competitorsList.add(competitor);

        competitorsTableObservableList.add(new CompetitorTableRow(competitor));

    }

    private void addCompetition() {

        if (competitionNameField.getText().matches("") ||  scoreInCompetitionField.getText().matches(""))
            return;

        Competition competition =
                new Competition(competitionNameField.getText(), Integer.valueOf(scoreInCompetitionField.getText()));

        tableCompetitors.getSelectionModel()
                .getSelectedItem().getCompetitorObject().addCompetition(competition);

        refreshCompetitionsTable();

    }


}
