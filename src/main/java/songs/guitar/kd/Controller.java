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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import songs.guitar.kd.dao.impl.ArtistDaoImpl;
import songs.guitar.kd.dao.impl.NoteDaoImpl;
import songs.guitar.kd.dao.impl.SongDaoImpl;
import songs.guitar.kd.model.*;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Song;
import songs.guitar.kd.util.HibernateUtil;


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

    private List<Competitor> songsList = new ArrayList<>();

    private SongDaoImpl songDao;
    private ArtistDaoImpl artistDao;
    private NoteDaoImpl noteDao;

    // ObservableList powoduje, że tabela automatycznie reaguje na zawartości listy tego typu
    private ObservableList<SongTableRow> songsTableObservableList = FXCollections.observableArrayList();

    @FXML
    TableView<SongTableRow> tableSongs;

    @FXML
    TableColumn<SongTableRow, String> artistColumn;

    @FXML
    TableColumn<SongTableRow, String> songColumn;

    @FXML
    TableColumn<SongTableRow, String> difficultyLevelColumn;

    @FXML
    TableColumn<SongTableRow, String> learnedLevelColumn;

    @FXML
    TableColumn<SongTableRow, String> notesColumn;

    @FXML
    TextField artistNameField, songTitleField, difficultyLevelField;

    @FXML
    Button addSongButton, showLearnedButton, showNotLearnedButton, showAllButton;

    /*
        The initialize() method is automatically called after the fxml file has been loaded.
         At this time, all the FXML fields should have been initialized already.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeObjects();

        setTables();

        createListeners();

        setCellFactoryForEachColumn();
    }

    private void initializeObjects() {

        songDao = new SongDaoImpl();
        artistDao = new ArtistDaoImpl();
        noteDao = new NoteDaoImpl();
    }


    private void setTables() {
        tableSongs.setItems(songsTableObservableList);



        songsTableObservableList.add(new SongTableRow("Fade to Black", "Metallica", "6/10", "9/10", "Ostatnie solo do szlifowania"));

        //testAddSong();

//        for (Competitor competitor : songsList) {
//            songsTableObservableList.add(new SongTableRow(competitor));
//        }

    }


    private void createListeners() {

        addSongButton.setOnAction((ActionEvent e) -> {
            addSong();
        });
        showLearnedButton.setOnAction((ActionEvent e) -> {
            listLearnedSongs();
        });
        showNotLearnedButton.setOnAction((ActionEvent e) -> {
            listNotLearnedSongs();
        });
        showAllButton.setOnAction((ActionEvent e) -> {
            listAllSongs();
        });

//        tableCompetitors.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//
//                    refreshCompetitionsTable();
//
//                });
    }

    private void refreshCompetitionsTable() {

//        competitionsTableObservableList.clear();
//
//        for(Competition competition : tableCompetitors.getSelectionModel()
//                .getSelectedItem().getCompetitorObject().getCompetitions()){
//
//            competitionsTableObservableList.add(new CompetitionTableRow(competition));
//        }
    }

    private void setCellFactoryForEachColumn() {
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistNameProperty());
        songColumn.setCellValueFactory(cellData -> cellData.getValue().songTitleNameProperty());
        difficultyLevelColumn.setCellValueFactory(cellData -> cellData.getValue().difficultyLevelProperty());
        learnedLevelColumn.setCellValueFactory(cellData -> cellData.getValue().learnedLevelProperty());
        notesColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

    }

    private void testAddSong(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();


        Artist artist = new Artist();
        artist.setArtistName("Metallica");

        Song song = new Song();
        song.setDifficultyLevel("6/10");
        song.setLearnedLevel("9/10");
        song.setTitle("Fade To Black");

        song.setArtist(artist);

        //session.persist(artist);
        session.persist(song);

        session.getTransaction().commit();


        session.close();
    }

    private void addSong() {

        Artist artist = new Artist();
        Song song = new Song();

        artist.setArtistName(artistNameField.getText());

        song.setArtist(artist);
        song.setTitle(songTitleField.getText());
        song.setDifficultyLevel(difficultyLevelField.getText());
        song.setLearnedLevel("0/0");

        songDao.saveSong(song);

//        Competitor competitor = new Competitor(competitorNameField.getText());
//
//        songsList.add(competitor);
//
//        songsTableObservableList.add(new CompetitorTableRow(competitor));

    }

    private void addCompetition() {

//        if (competitionNameField.getText().matches("") ||  scoreInCompetitionField.getText().matches(""))
//            return;
//
//        Competition competition =
//                new Competition(competitionNameField.getText(), Integer.valueOf(scoreInCompetitionField.getText()));
//
//        tableCompetitors.getSelectionModel()
//                .getSelectedItem().getCompetitorObject().addCompetition(competition);
//
//        refreshCompetitionsTable();

    }

    private void listAllSongs() {

    }

    private void listLearnedSongs() {

    }

    private void listNotLearnedSongs() {

    }

}
