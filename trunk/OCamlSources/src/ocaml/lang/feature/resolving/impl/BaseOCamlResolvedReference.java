/*
 * OCaml Support For IntelliJ Platform.
 * Copyright (C) 2010 Maxim Manuylov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */

package ocaml.lang.feature.resolving.impl;

import com.intellij.lang.ASTNode;
import ocaml.lang.feature.resolving.OCamlNamedElement;
import ocaml.lang.feature.resolving.OCamlResolvedReference;
import ocaml.lang.feature.resolving.ResolvingBuilder;
import ocaml.lang.processing.parser.psi.OCamlElement;
import ocaml.lang.processing.parser.psi.OCamlElementProcessorAdapter;
import ocaml.lang.processing.parser.psi.OCamlPsiUtil;
import ocaml.lang.processing.parser.psi.element.OCamlModuleDefinitionBinding;
import ocaml.lang.processing.parser.psi.element.OCamlModuleSpecificationBinding;
import ocaml.util.OCamlStringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Maxim.Manuylov
 *         Date: 28.03.2009
 */
public abstract class BaseOCamlResolvedReference extends BaseOCamlNamedElement implements OCamlResolvedReference {
    protected BaseOCamlResolvedReference(@NotNull final ASTNode node) {
        super(node);
    }

    @Nullable
    public String getCanonicalPath() { //todo 
        final StringBuilder sb = new StringBuilder(OCamlStringUtil.getNotNull(getName()));

        final OCamlElementProcessorAdapter processor = new OCamlElementProcessorAdapter() {
            public void process(@NotNull final OCamlElement psiElement) {
                if (psiElement instanceof OCamlModuleDefinitionBinding || psiElement instanceof OCamlModuleSpecificationBinding) {
                    sb.insert(0, ".");
                    sb.insert(0, OCamlStringUtil.getNotNull(((OCamlNamedElement) psiElement).getName()));
                }
            }
        };

        OCamlElement parent = OCamlPsiUtil.getParent(this);

        while (parent != null) {
            parent.accept(processor);
            parent = OCamlPsiUtil.getParent(parent);
        }

        return sb.toString();
    }

    @Override
    public boolean processDeclarations(@NotNull final ResolvingBuilder builder) {
        return builder.getProcessor().process(this);
    }
}
