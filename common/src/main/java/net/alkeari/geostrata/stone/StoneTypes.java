package net.alkeari.geostrata.stone;

import java.util.List;
import java.util.stream.Collectors;

public final class StoneTypes {
    private StoneTypes() {}

    // Igneous (12)
    public static final StoneType ANDESITE       = new StoneType("andesite",       RockCategory.IGNEOUS,     1.5f, 10f,  0);
    public static final StoneType BASALT         = new StoneType("basalt",         RockCategory.IGNEOUS,     5.0f, 100f, 2);
    public static final StoneType DIORITE        = new StoneType("diorite",        RockCategory.IGNEOUS,     1.5f, 10f,  0);
    public static final StoneType GRANITE        = new StoneType("granite",        RockCategory.IGNEOUS,     3.0f, 15f,  1);
    public static final StoneType RHYOLITE       = new StoneType("rhyolite",       RockCategory.IGNEOUS,     1.5f, 10f,  0);
    public static final StoneType PEGMATITE      = new StoneType("pegmatite",      RockCategory.IGNEOUS,     1.5f, 10f,  0);
    public static final StoneType DIABASE        = new StoneType("diabase",        RockCategory.IGNEOUS,     5.0f, 100f, 2);
    public static final StoneType GABBRO         = new StoneType("gabbro",         RockCategory.IGNEOUS,     5.0f, 100f, 2);
    public static final StoneType PERIDOTITE     = new StoneType("peridotite",     RockCategory.IGNEOUS,     3.0f, 15f,  0);
    public static final StoneType BASALTIC_GLASS = new StoneType("basaltic_glass", RockCategory.IGNEOUS,     3.0f, 15f,  0);
    public static final StoneType SCORIA         = new StoneType("scoria",         RockCategory.IGNEOUS,     1.0f, 7f,   0);
    public static final StoneType TUFF           = new StoneType("tuff",           RockCategory.IGNEOUS,     2.0f, 10f,  0);

    // Metamorphic (8)
    public static final StoneType SLATE          = new StoneType("slate",          RockCategory.METAMORPHIC, 1.5f, 10f,  0);
    public static final StoneType SCHIST         = new StoneType("schist",         RockCategory.METAMORPHIC, 3.0f, 15f,  1);
    public static final StoneType GNEISS         = new StoneType("gneiss",         RockCategory.METAMORPHIC, 3.0f, 15f,  1);
    public static final StoneType PHYLLITE       = new StoneType("phyllite",       RockCategory.METAMORPHIC, 1.5f, 10f,  0);
    public static final StoneType AMPHIBOLITE    = new StoneType("amphibolite",    RockCategory.METAMORPHIC, 3.0f, 15f,  1);
    public static final StoneType HORNFELS       = new StoneType("hornfels",       RockCategory.METAMORPHIC, 3.0f, 15f,  1);
    public static final StoneType QUARTZITE      = new StoneType("quartzite",      RockCategory.METAMORPHIC, 4.0f, 15f,  1);
    public static final StoneType NOVACULITE     = new StoneType("novaculite",     RockCategory.METAMORPHIC, 3.0f, 15f,  1);

    // Sedimentary (7)
    public static final StoneType SHALE          = new StoneType("shale",          RockCategory.SEDIMENTARY, 1.5f, 10f,  0);
    public static final StoneType CONGLOMERATE   = new StoneType("conglomerate",   RockCategory.SEDIMENTARY, 1.5f, 10f,  0);
    public static final StoneType DOLOMITE       = new StoneType("dolomite",       RockCategory.SEDIMENTARY, 3.0f, 15f,  1);
    public static final StoneType LIMESTONE      = new StoneType("limestone",      RockCategory.SEDIMENTARY, 1.5f, 10f,  0);
    public static final StoneType MARBLE         = new StoneType("marble",         RockCategory.SEDIMENTARY, 1.5f, 10f,  0);
    public static final StoneType SILTSTONE      = new StoneType("siltstone",      RockCategory.SEDIMENTARY, 1.0f, 10f,  0);
    public static final StoneType ROCK_SALT      = new StoneType("rock_salt",      RockCategory.SEDIMENTARY, 1.5f, 10f,  0);

    public static final List<StoneType> ALL = List.of(
        ANDESITE, BASALT, DIORITE, GRANITE, RHYOLITE, PEGMATITE,
        DIABASE, GABBRO, PERIDOTITE, BASALTIC_GLASS, SCORIA, TUFF,
        SLATE, SCHIST, GNEISS, PHYLLITE, AMPHIBOLITE, HORNFELS, QUARTZITE, NOVACULITE,
        SHALE, CONGLOMERATE, DOLOMITE, LIMESTONE, MARBLE, SILTSTONE, ROCK_SALT
    );

    public static final List<StoneType> IGNEOUS = ALL.stream()
        .filter(t -> t.category() == RockCategory.IGNEOUS).collect(Collectors.toList());
    public static final List<StoneType> METAMORPHIC = ALL.stream()
        .filter(t -> t.category() == RockCategory.METAMORPHIC).collect(Collectors.toList());
    public static final List<StoneType> SEDIMENTARY = ALL.stream()
        .filter(t -> t.category() == RockCategory.SEDIMENTARY).collect(Collectors.toList());

    public static List<StoneType> forCategory(RockCategory category) {
        return switch (category) {
            case IGNEOUS -> IGNEOUS;
            case METAMORPHIC -> METAMORPHIC;
            case SEDIMENTARY -> SEDIMENTARY;
        };
    }
}
