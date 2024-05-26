package algorithmsTests

import model.algorithms.communityDetection.CommunityDetectionFinder
import model.graphs.graphs.Graph
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CommunityDetectionFinderTests {
    private lateinit var graph: Graph<Int>
    private lateinit var finder: CommunityDetectionFinder<Int>

    @BeforeEach
    fun setup() {
        graph = Graph()
    }

    @Test
    @DisplayName("empty")
    fun empty() {
        finder = CommunityDetectionFinder(graph)

        val result = finder.findCommunities()
        assertTrue(result.isEmpty())
    }
}