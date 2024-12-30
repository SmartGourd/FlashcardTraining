package cz.cuni.mff.nagylad.routing;

import cz.cuni.mff.nagylad.model.AppState;
import cz.cuni.mff.nagylad.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    private App testApp;
    private AppState testAppState;
    private Router router;

    @BeforeEach
    void setUp() {
        // Create instances of test stubs for App and AppState
        testApp = new App();
        testAppState = new AppState();
        router = new Router(testApp, testAppState);
    }

    @Test
    void testChangePageToMainPage() {
        // Act
        router.changePage(AvailablePages.MAIN_PAGE);

        // Assert
        assertInstanceOf(MainPage.class, testApp.page, "Expected MainPage instance.");
        assertNotNull(testApp.page, "Page should not be null.");
    }

    @Test
    void testChangePageToSetsPage() {
        // Act
        router.changePage(AvailablePages.SETS_PAGE);

        // Assert
        assertInstanceOf(SetsPage.class, testApp.page, "Expected SetsPage instance.");
        assertNotNull(testApp.page, "Page should not be null.");
    }

    @Test
    void testChangePageToSetDetailPage() {
        // Arrange
        int setIndex = 1;

        // Act
        router.changePage(AvailablePages.SET_DETAIL_PAGE, setIndex);

        // Assert
        assertInstanceOf(SetDetailPage.class, testApp.page, "Expected SetDetailPage instance.");
        assertNotNull(testApp.page, "Page should not be null.");
    }

    @Test
    void testChangePageToTrainingPage() {
        // Arrange
        int setIndex = 2;

        // Act
        router.changePage(AvailablePages.TRAINING_PAGE, setIndex);

        // Assert
        assertInstanceOf(TrainingPage.class, testApp.page, "Expected TrainingPage instance.");
        assertNotNull(testApp.page, "Page should not be null.");
    }

    @Test
    void testChangePageToGamePage() {
        // Act
        router.changePage(AvailablePages.GAME_PAGE);

        // Assert
        assertInstanceOf(GamePage.class, testApp.page, "Expected GamePage instance.");
        assertNotNull(testApp.page, "Page should not be null.");
    }

    @Test
    void testChangePageWithoutSetThrowsException() {
        // Assert & Act
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                router.changePage(AvailablePages.SET_DETAIL_PAGE)
        );
        assertEquals("The set needs to be selected for the SetDetailPage or TrainingPage", exception.getMessage());
    }

    @Test
    void testChangePageToTrainingWithoutSetThrowsException() {
        // Assert & Act
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                router.changePage(AvailablePages.TRAINING_PAGE)
        );
        assertEquals("The set needs to be selected for the SetDetailPage or TrainingPage", exception.getMessage());
    }
}
