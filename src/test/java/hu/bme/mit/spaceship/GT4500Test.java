package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore primary;
  private TorpedoStore secondary;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    primary=mock( TorpedoStore.class);
    secondary=mock( TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(0)).isEmpty();
  }

  
  @Test
  public void fireTorpedo_All_Secondary_Empty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_p(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(0)).isEmpty();
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_s(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();
    verify(primary, times(0)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Firstfired(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primary, times(1)).isEmpty();
    verify(secondary, times(1)).isEmpty();
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Firstfired_Secondempty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primary, times(2)).isEmpty();
    verify(secondary, times(1)).isEmpty();
    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);
  }



}
