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

package ocaml.lang.processing.parser.psi.element.impl;

import com.intellij.lang.ASTNode;
import ocaml.lang.feature.resolving.NameType;
import ocaml.lang.feature.resolving.ResolvingBuilder;
import ocaml.lang.feature.resolving.impl.BaseOCamlResolvedReference;
import ocaml.lang.feature.resolving.util.OCamlDeclarationsUtil;
import ocaml.lang.processing.parser.psi.OCamlElementVisitor;
import ocaml.lang.processing.parser.psi.OCamlPsiUtil;
import ocaml.lang.processing.parser.psi.element.OCamlFieldName;
import ocaml.lang.processing.parser.psi.element.OCamlFieldPath;
import ocaml.lang.processing.parser.psi.element.OCamlPattern;
import ocaml.lang.processing.parser.psi.element.OCamlRecordFieldInitializationInPattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Maxim.Manuylov
 *         Date: 17.04.2010
 */
public class OCamlRecordFieldInitializationInPatternImpl extends BaseOCamlResolvedReference implements OCamlRecordFieldInitializationInPattern {
    public OCamlRecordFieldInitializationInPatternImpl(@NotNull final ASTNode node) {
        super(node);
    }

    @Nullable
    public ASTNode getNameElement() {
        final OCamlFieldPath fieldPath = OCamlPsiUtil.getFirstChildOfType(this, OCamlFieldPath.class);
        if (fieldPath == null) return null;
        final OCamlFieldName fieldName = fieldPath.getFieldName();
        return fieldName == null ? null : fieldName.getNameElement();
    }

    @NotNull
    public NameType getNameType() {
        return NameType.LowerCase;
    }

    @NotNull
    public String getDescription() {
        return "field";
    }

    public void visit(@NotNull final OCamlElementVisitor visitor) {
        visitor.visitRecordFieldInitializationInPattern(this);
    }

    @Override
    public boolean processDeclarations(@NotNull final ResolvingBuilder builder) {
        return builder.getProcessor().process(this)
            || OCamlDeclarationsUtil.processDeclarationsInChildren(builder, this, OCamlPattern.class);
    }
}