import com.google.gson.annotations.SerializedName

data class EvolutionResponse(
    @SerializedName("chain")
    val chain: EvolutionChain
)

data class EvolutionChain(
    val species: PokemonSpecies,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolutionLink> = emptyList(),
)

data class EvolutionLink(
    val species: PokemonSpecies,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolutionLink> = emptyList(),
)

data class EvolutionTrigger(
    val name: String,
    val url: String
)

data class PokemonSpecies(
    val name: String,
    val url: String
)