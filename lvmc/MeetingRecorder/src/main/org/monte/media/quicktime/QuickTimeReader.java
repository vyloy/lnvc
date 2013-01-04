/*
 * @(#)QuickTimeReader.java  
 * 
 * Copyright (c) 2012 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 * 
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package org.monte.media.quicktime;

import java.io.File;
import java.io.IOException;
import javax.imageio.stream.ImageInputStream;
import org.monte.media.Buffer;
import org.monte.media.Format;
import org.monte.media.MovieReader;
import org.monte.media.math.Rational;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

/**
 * {@code QuickTimeReader}.
 *
 * @author Werner Randelshofer
 * @version $Id: QuickTimeReader.java 251 2012-07-29 22:17:17Z werner $
 */
public class QuickTimeReader extends QuickTimeInputStream implements MovieReader {

      public final static Format QUICKTIME = new Format(MediaTypeKey,MediaType.FILE,MimeTypeKey,MIME_QUICKTIME);
  /**
     * Creates a new instance.
     *
     * @param file the input file
     */
    public QuickTimeReader(File file) throws IOException {
        super(file);
    }

    /**
     * Creates a new instance.
     *
     * @param in the input stream.
     */
    public QuickTimeReader(ImageInputStream in) throws IOException {
        super(in);
    }

    @Override
    public long timeToSample(int track, Rational seconds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rational sampleToTime(int track, long sample) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Format getFileFormat() throws IOException {
        return QUICKTIME;
    }

    @Override
    public Format getFormat(int track) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getChunkCount(int track) throws IOException {
        ensureRealized();
        return tracks.get(track).sampleCount;
    }

    @Override
    public void read(int track, Buffer buffer) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int nextTrack() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMovieReadTime(Rational newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rational getReadTime(int track) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rational getDuration() throws IOException {
        return new Rational(getMovieDuration(), getMovieTimeScale());
    }
    @Override
    public Rational getDuration(int track) throws IOException {
        ensureRealized();
        Track tr = tracks.get(track);
        // FIXME - This method must take the edit list of the track into account
        Rational trackDuration = new Rational(tr.mediaDuration,tr.mediaTimeScale);
        return trackDuration;
    }

    @Override
    public int findTrack(int fromTrack, Format format) throws IOException {
        for (int i=fromTrack,n=getTrackCount();i<n;i++) {
            if (getFormat(i).matches(format)) {
                return i;
            }
        }
        return -1;
    }
}
