package org.iammikrostoritve;

import org.iammikrostoritve.entity.Artist;
import org.iammikrostoritve.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.bson.types.ObjectId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistRepositoryTest {

    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() throws Exception {
        ObjectId id = new ObjectId();
        Artist artist = new Artist();
        artist.id = id;
        artist.setArtistName("Test Artist");

        when(artistRepository.getById(id.toString())).thenReturn(artist);

        Artist response = artistRepository.getById(id.toString());

        assertEquals(artist, response);
        verify(artistRepository).getById(id.toString());
    }
}