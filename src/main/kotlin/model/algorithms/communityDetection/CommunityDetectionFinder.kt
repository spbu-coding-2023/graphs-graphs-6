package model.algorithms.communityDetection

import model.graphs.graphs.Graph

open class CommunityDetectionFinder<V>(protected val graph: Graph<V>) {
    fun findCommunities(): List<Int> {
        return emptyList()
    }
}