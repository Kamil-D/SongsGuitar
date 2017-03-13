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
import songs.guitar.kd.gui.TextFieldLimiter;
import songs.guitar.kd.model.*;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Note;
import songs.guitar.kd.model.db.Song;
import songs.guitar.kd.util.HibernateUtil;
import songs.guitar.kd.util.Util;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;

/*
https://www.mkyong.com/java/jaxb-hello-world-example/
http://code.makery.ch/library/javafx-8-tutorial/part2/
http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
http://www.java2s.com/Tutorials/Java/JavaFX/0650__JavaFX_TableView.htm
https://examples.javacodegeeks.com/desktop-java/javafx/tableview/javafx-tableview-example/
 */

public class Controller implements Initializable {

    private TextFieldLimiter textFieldLimiter;

    private List<Song> songsList;
    private List<Artist> artistList;
    private List<Note> noteList;

    private SongDao songDao;
    private ArtistDao artistDao;
    private NoteDao noteDao;

    // ObservableList powoduje, że tabela automatycznie reaguje na zawartości listy tego typu
    private ObservableList<SongTableRow> songsTableObservableList = FXCollections.observableArrayList();

    private HashSet<String> artistNamesStringHashSet;

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

        initializeArtistNamesList();

        initializeAutoCompletionArtistTextField();
    }

    private void initializeObjects() {

        textFieldLimiter = new TextFieldLimiter();

        songDao = new SongDaoImpl();
        artistDao = new ArtistDaoImpl();
        noteDao = new NoteDaoImpl();

        songsList = new ArrayList<>();
        artistList = new ArrayList<>();
        noteList = new ArrayList<>();

        artistNamesStringHashSet = new HashSet<>();

        addTextLimiter();
        initializeComboBox();

    }

    private void initializeComboBox() {
        difficultyLevelComboBox.getItems().addAll(Util.difficultyLevelStrings);
        difficultyLevelComboBox.setValue(Util.difficultyLevelStrings[0]);
    }

    private void addTextLimiter() {

        textFieldLimiter.addTextLimiter(artistNameField, 50);
        textFieldLimiter.addTextLimiter(songTitleField, 80);
    }

    private void setTables() {

        tableSongs.setItems(songsTableObservableList);

        downloadAllSongsFromDBAndRefreshSongTable();

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
            downloadLearnedSongsFromDBAndRefreshSongTable();
        });
        showNotLearnedButton.setOnAction((ActionEvent e) -> {
            downloadNotLearnedSongsFromDBAndRefreshSongTable();
        });
        showAllButton.setOnAction((ActionEvent e) -> {
            downloadAllSongsFromDBAndRefreshSongTable();
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

        if ( ! checkFieldsFilled() ) {
            showAlertDialog();
            return;
        }

        Song song = new Song();
        Artist artist = new Artist();
        Note note = new Note();


//        if ( ifArtistExists(artistName) )
//            artist = artistDao.getArtistByName(artistName);
//        else
//            artist.setArtistName(artistName);

        note.setNoteText("PUSTA NOTATKA");

        song.setTitle(songTitleField.getText());
        song.setDifficultyLevel(difficultyLevelComboBox.getSelectionModel().getSelectedItem().toString());
        song.setLearnedLevel(Util.learnedLevelStrings[0]);

//        song.setArtist(artist);
        song.setNote(note);

        String artistName = artistNameField.getText();

        if ( ifArtistExists(artistName) )
            songDao.saveSongExistingArtist(song, artistName);
        else {
            artist.setArtistName(artistName);
            song.setArtist(artist);
            songDao.saveSong(song);
        }

        clearFields();
        downloadAllSongsFromDBAndRefreshSongTable();
        initializeArtistNamesList();
        initializeAutoCompletionArtistTextField();
    }

    private void showAlertDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Puste pola");
        alert.setHeaderText(null);
        alert.setContentText("Uzupełnij wszystkie pola!");
        alert.showAndWait();
    }

    private boolean checkFieldsFilled() {

        if ( artistNameField.getText().length() <= 0 )
            return false;
        else if ( songTitleField.getText().length() <= 0 )
            return false;
        else
            return true;
    }

    private boolean ifArtistExists(String artistName) {

        if ( !artistNamesStringHashSet.contains(artistName) )
            return false;
        else
            return true;
    }

    private void clearFields() {
        artistNameField.clear();
        songTitleField.clear();
        difficultyLevelComboBox.setValue(Util.difficultyLevelStrings[0]);
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

    private void downloadAllSongsFromDBAndRefreshSongTable() {

        clearAllLists();
        getAllSongsFromDB();
        refreshTable();
    }

    private void downloadNotLearnedSongsFromDBAndRefreshSongTable() {

        clearAllLists();
        getNotLearnedSongsFromDB();
        refreshTable();
    }

    private void downloadLearnedSongsFromDBAndRefreshSongTable() {

        clearAllLists();
        getLearnedSongsFromDB();
        refreshTable();
    }

    private void clearAllLists() {

        songsTableObservableList.clear();
        artistNamesStringHashSet.clear();
        songsList.clear();
        artistList.clear();
        noteList.clear();
    }

    private void getAllSongsFromDB() {
        songsList = songDao.getAllSongs();
    }

    private void getArtistsFromDB() {
        artistList = artistDao.getAllArtist();
    }

    private void getLearnedSongsFromDB() {

        songsList = songDao.getLearnedSongs();
    }

    private void getNotLearnedSongsFromDB() {

        songsList = songDao.getNotLearnedSongs();
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

    private void initializeArtistNamesList() {

        getArtistsFromDB();

        for ( Artist artist : artistList )
            artistNamesStringHashSet.add(artist.getArtistName());
    }

    private void initializeAutoCompletionArtistTextField() {

        TextFields.bindAutoCompletion(artistNameField, artistNamesStringHashSet);
    }

}
