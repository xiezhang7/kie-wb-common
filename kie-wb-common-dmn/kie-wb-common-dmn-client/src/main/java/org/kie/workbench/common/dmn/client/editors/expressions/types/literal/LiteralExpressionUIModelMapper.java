/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.dmn.client.editors.expressions.types.literal;

import java.util.Optional;
import java.util.function.Supplier;

import org.kie.workbench.common.dmn.api.definition.model.LiteralExpression;
import org.kie.workbench.common.dmn.client.widgets.grid.controls.list.ListSelectorView;
import org.kie.workbench.common.dmn.client.widgets.grid.model.BaseUIModelMapper;
import org.uberfire.ext.wires.core.grids.client.model.GridCellValue;
import org.uberfire.ext.wires.core.grids.client.model.GridData;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridCellValue;

public class LiteralExpressionUIModelMapper extends BaseUIModelMapper<LiteralExpression> {

    private final ListSelectorView.Presenter listSelector;

    public LiteralExpressionUIModelMapper(final Supplier<GridData> uiModel,
                                          final Supplier<Optional<LiteralExpression>> dmnModel,
                                          final ListSelectorView.Presenter listSelector) {
        super(uiModel,
              dmnModel);
        this.listSelector = listSelector;
    }

    @Override
    public void fromDMNModel(final int rowIndex,
                             final int columnIndex) {
        dmnModel.get().ifPresent(literalExpression -> {
            uiModel.get().setCell(rowIndex,
                                  columnIndex,
                                  () -> new LiteralExpressionCell<>(new BaseGridCellValue<>(literalExpression.getText().getValue()),
                                                                    listSelector));
        });
    }

    @Override
    public void toDMNModel(final int rowIndex,
                           final int columnIndex,
                           final Supplier<Optional<GridCellValue<?>>> cell) {
        dmnModel.get().ifPresent(literalExpression -> literalExpression.getText().setValue((String) cell.get().orElse(new BaseGridCellValue<>("")).getValue()));
    }
}
