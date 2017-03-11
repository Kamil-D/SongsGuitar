package songs.guitar.kd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import songs.guitar.kd.dao.ArtistDao;
import songs.guitar.kd.dao.NoteDao;
import songs.guitar.kd.dao.SongDao;
import songs.guitar.kd.dao.impl.ArtistDaoImpl;
import songs.guitar.kd.dao.impl.NoteDaoImpl;
import songs.guitar.kd.dao.impl.SongDaoImpl;
import songs.guitar.kd.model.*;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Note;
import songs.guitar.kd.model.db.Song;
import songs.guitar.kd.util.HibernateUtil;
import songs.guitar.kd.util.Util;


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

    private List<Song> songsList;
    private List<Artist> artistList;
    private List<Note> noteList;

    private SongDao songDao;
    private ArtistDao artistDao;
    private NoteDao noteDao;

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
    TextField artistNameField, songTitleField;

    @FXML
    Button addSongButton, showLearnedButton, showNotLearnedButton, showAllButton;

    @FXML
    ComboBox difficultyLevelComboBox;

    /*
        The initialize() method is automatically called after the fxml file has been loaded.
         At this time, all the FXML fields should have been initialized already.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
         * Tymczasowe i częściowe naprawienie błędu NullPointerException przy starcie aplikacji
         * Uwaga - nadal wyrzuca przy configuration.buildSessionFactory(serviceRegistry) w klasie HibernateUtil
         * Pomimo tego aplikacja uruchamia się bez większych problemów i prawidłowo pobrane zostają dane z BD
         */
        HibernateUtil.getSessionFactory();

        initializeObjects();

        setTables();

        createListeners();

        setCellFactoryForEachColumn();
    }

    private void initializeObjects() {

        songDao = new SongDaoImpl();
        artistDao = new ArtistDaoImpl();
        noteDao = new NoteDaoImpl();

        songsList = new ArrayList<>();
        artistList = new ArrayList<>();
        noteList = new ArrayList<>();

        initializeComboBox();

    }

    private void initializeComboBox() {
        difficultyLevelComboBox.getItems().addAll(Util.difficultyLevelStrings);
        difficultyLevelComboBox.setValue(Util.difficultyLevelStrings[0]);
    }


    private void setTables() {

        tableSongs.setItems(songsTableObservableList);

        downloadDataFromDBAndRefreshSongTable();

//        Song song = new Song();
//        Artist artist = new Artist();
//        Note note = new Note();

//        artist.setArtistName("Metallica");
//        song.setTitle("Fade To Black");
//        song.setDifficultyLevel("6/10");
//        song.setLearnedLevel("9/10");
//        note.setNoteText("Ostatnie solo do szlifowania");
//
//        songsTableObservableList.add(new SongTableRow(song, artist, note));

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
            downloadDataFromDBAndRefreshSongTable();
        });

//        tableCompetitors.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//
//                    refreshTable();
//
//                });
    }

    private void setCellFactoryForEachColumn() {
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistNameProperty());
        songColumn.setCellValueFactory(cellData -> cellData.getValue().songTitleNameProperty());
        difficultyLevelColumn.setCellValueFactory(cellData -> cellData.getValue().difficultyLevelProperty());
        learnedLevelColumn.setCellValueFactory(cellData -> cellData.getValue().learnedLevelProperty());
        notesColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty());

    }

    private void addSong() {

        Song song = new Song();
        Artist artist = new Artist();
        Note note = new Note();

        artist.setArtistName(artistNameField.getText());

        note.setNoteText("PUSTA NOTATKA");

        song.setTitle(songTitleField.getText());
        song.setDifficultyLevel(difficultyLevelComboBox.getSelectionModel().getSelectedItem().toString());
        song.setLearnedLevel("0/0");

        song.setArtist(artist);
        song.setNote(note);

        songDao.saveSong(song);

        downloadDataFromDBAndRefreshSongTable();
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
//        refreshTable();

    }

    private void downloadDataFromDBAndRefreshSongTable() {

        clearAllLists();
        initializeAllListsFromDB();
        refreshTable();
    }

    private void clearAllLists() {

        songsTableObservableList.clear();

        songsList.clear();
        artistList.clear();
        noteList.clear();
    }

    private void initializeAllListsFromDB() {
        songsList = songDao.getAllSongs();
        //artistList = artistDao.getAllArtist();
        //noteList = noteDao.getAllNotes();
    }

    private void listLearnedSongs() {

    }

    private void listNotLearnedSongs() {

    }

    private void refreshTable() {

        Song song;
        Artist artist;
        Note note;

        for ( int i=0 ; i<songsList.size() ; i++) {

            song = songsList.get(i);
            artist = songsList.get(i).getArtist();
            note = songsList.get(i).getNote();

            songsTableObservableList.
                    add(new SongTableRow(song, artist, note));
        }

//        competitionsTableObservableList.clear();
//
//        for(Competition competition : tableCompetitors.getSelectionModel()
//                .getSelectedItem().getCompetitorObject().getCompetitions()){
//
//            competitionsTableObservableList.add(new CompetitionTableRow(competition));
//        }
    }

}
