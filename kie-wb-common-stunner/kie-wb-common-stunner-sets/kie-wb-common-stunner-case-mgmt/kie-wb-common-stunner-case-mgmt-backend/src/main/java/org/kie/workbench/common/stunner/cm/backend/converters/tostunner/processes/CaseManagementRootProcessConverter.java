/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.common.stunner.cm.backend.converters.tostunner.processes;

import org.eclipse.bpmn2.Process;
import org.kie.workbench.common.stunner.bpmn.backend.converters.TypedFactoryManager;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.BaseConverterFactory;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.DefinitionResolver;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.processes.BaseRootProcessConverter;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.properties.ProcessPropertyReader;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.properties.PropertyReaderFactory;
import org.kie.workbench.common.stunner.bpmn.definition.property.diagram.AdHoc;
import org.kie.workbench.common.stunner.bpmn.definition.property.diagram.Executable;
import org.kie.workbench.common.stunner.bpmn.definition.property.diagram.GlobalVariables;
import org.kie.workbench.common.stunner.bpmn.definition.property.diagram.Id;
import org.kie.workbench.common.stunner.bpmn.definition.property.general.Documentation;
import org.kie.workbench.common.stunner.bpmn.definition.property.general.Name;
import org.kie.workbench.common.stunner.bpmn.definition.property.general.SLADueDate;
import org.kie.workbench.common.stunner.cm.definition.CaseManagementDiagram;
import org.kie.workbench.common.stunner.cm.definition.property.diagram.DiagramSet;
import org.kie.workbench.common.stunner.cm.definition.property.diagram.Package;
import org.kie.workbench.common.stunner.cm.definition.property.diagram.ProcessInstanceDescription;
import org.kie.workbench.common.stunner.cm.definition.property.diagram.Version;
import org.kie.workbench.common.stunner.cm.definition.property.variables.ProcessData;
import org.kie.workbench.common.stunner.cm.definition.property.variables.ProcessVariables;
import org.kie.workbench.common.stunner.core.graph.Edge;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.kie.workbench.common.stunner.core.graph.content.view.View;

public class CaseManagementRootProcessConverter extends BaseRootProcessConverter<CaseManagementDiagram, DiagramSet, ProcessData> {

    private TypedFactoryManager factoryManager;

    public CaseManagementRootProcessConverter(TypedFactoryManager typedFactoryManager,
                                              PropertyReaderFactory propertyReaderFactory,
                                              DefinitionResolver definitionResolver,
                                              BaseConverterFactory factory) {
        super(typedFactoryManager, propertyReaderFactory, definitionResolver, factory);

        this.factoryManager = typedFactoryManager;
    }

    @Override
    protected Node<View<CaseManagementDiagram>, Edge> createNode(String id) {
        return factoryManager.newNode(id, CaseManagementDiagram.class);
    }

    @Override
    protected DiagramSet createDiagramSet(Process process, ProcessPropertyReader e) {
        return new DiagramSet(new Name(process.getName()),
                              new Documentation(e.getDocumentation()),
                              new Id(process.getId()),
                              new Package(e.getPackage()),
                              new Version(e.getVersion()),
                              new AdHoc(e.isAdHoc()),
                              new ProcessInstanceDescription(e.getDescription()),
                              new GlobalVariables(e.getGlobalVariables()),
                              new Executable(process.isIsExecutable()),
                              new SLADueDate(e.getSlaDueDate()));
    }

    @Override
    protected ProcessData createProcessData(String processVariables) {
        return new ProcessData(new ProcessVariables(processVariables));
    }
}
