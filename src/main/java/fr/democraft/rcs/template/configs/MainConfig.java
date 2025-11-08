package fr.democraft.rcs.template.configs;

import group.aelysium.rustyconnector.shaded.group.aelysium.declarative_yaml.DeclarativeYAML;
import group.aelysium.rustyconnector.shaded.group.aelysium.declarative_yaml.annotations.Comment;
import group.aelysium.rustyconnector.shaded.group.aelysium.declarative_yaml.annotations.Config;
import group.aelysium.rustyconnector.shaded.group.aelysium.declarative_yaml.annotations.Namespace;
import group.aelysium.rustyconnector.shaded.group.aelysium.declarative_yaml.annotations.Node;

// You need to replace template in "/smart/template.yml" to your module ID to get your own config.
@Namespace("rustyconnector-modules")
@Config("/smart/template.yml")
public class MainConfig {
    @Node()
    public String myConfigEntry = "OMG I WORKED FIRST TIME";

    public static MainConfig New() {
        return DeclarativeYAML.From(MainConfig.class);
    }
}
