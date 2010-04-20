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

package ocaml.lang.processing.parser.psi.element;

import ocaml.lang.processing.parser.psi.OCamlElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Maxim.Manuylov
 *         Date: 21.03.2009
 */
public interface OCamlParentheses extends OCamlExpression,
                                          OCamlPattern,
                                          OCamlTypeExpression,
                                          OCamlClassExpression,
                                          OCamlModuleExpression,
                                          OCamlTypeParameters,
                                          OCamlModuleType {
    @Nullable
    <T extends OCamlElement> T getInternalElement(@NotNull final Class<T> type);
}
