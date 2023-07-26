package kkot.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // [A ver2] Start
        List<String> autoConfigs = new ArrayList<>();

        // [B ver3] Start
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
        // [B ver3] end

        // [C ver1] Start
        return autoConfigs.toArray(new String[0]);
        // [C ver1] End

        // [A ver2] End
    }
}
        /*
        [A ver1]
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        [B ver1]
        for(String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
            autoConfigs.add(candidate);
        }

        [B ver2]
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->
                autoConfigs.add(candidate)
        );

        [C ver2]
        return autoConfigs.stream().toArray(String[]::new);

        [C ver3]
        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
         */
