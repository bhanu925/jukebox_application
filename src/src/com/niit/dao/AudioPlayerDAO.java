package com.niit.dao;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface AudioPlayerDAO {
    void play();
    void farward();
    void pause();
    void resumeAudio()  throws UnsupportedAudioFileException,
            IOException, LineUnavailableException;
    void restart()throws IOException, LineUnavailableException,
            UnsupportedAudioFileException;
    void stop()throws UnsupportedAudioFileException,
            IOException, LineUnavailableException;
    void resetAudioStream()throws UnsupportedAudioFileException, IOException,
            LineUnavailableException;
    void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException;

}
