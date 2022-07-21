package com.imc.siemens_aas.aasenv.submodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imc.siemens_aas.aasenv.submodel.metamodel.SemanticId;
import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.*;
import com.imc.siemens_aas.utils.TypeUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;


@Data
@JsonIgnoreProperties(value = {"modelObject"})
public class Submodel {
    private SemanticId semanticId;
    private Identification identification;
    private String idShort;
    private String category;
    private ModelType modelType;
    private List<SubmodelElement> submodelElements;
    private String kind;
    private List<Description> descriptions;

    private ModelObject modelObject;

    public Submodel setModelObject(ModelObject modelObject) {
        this.modelObject = modelObject;
        return this;
    }

    /**
     * 刷新modelObject的属性值到submodel，主要修改submodelElements
     */
    public Submodel refreshValue() {
        refreshField(modelObject, submodelElements);
        return this;
    }

    private void refreshField(Object model, List<SubmodelElement> elements) {
        Class<?> clazz = model.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                //拿字段的值
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(model);
                Class<?> type = field.getType();

                if (TypeUtils.isPrimitive(type)) {//如果是基本数据类型
                    Iterator<SubmodelElement> iterator = elements.iterator();
                    while (iterator.hasNext()) {
                        SubmodelElement next = iterator.next();
                        if (next.getIdShort().equals(name)) {
                            ((Property) next).setValue(value);
                            break;
                        }
                    }
                    continue;
                }

                //如果是基本数据类型，到这里已经处理完break了，所以下面的代码对应非基本数据类型
                //如果不是基本数据类型
                Iterator<SubmodelElement> iterator = elements.iterator();
                while (iterator.hasNext()) {
                    SubmodelElement next = iterator.next();
                    if (next.getIdShort().equals(name)) {
                        List<SubmodelElement> elementList = ((SubmodelElementCollection) next).getValue();
                        refreshField(value, elementList);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("实体-属性转换失败");
            }
        }
    }

    /**
     * 获取Submodel中所有的Operation
     * @return
     */
    public HashMap<String, Operation> operations() {
        HashMap<String, Operation> resOperations = new HashMap<>();
        operations(resOperations, submodelElements);
        return resOperations;
    }

    /**
     * 将submodelElements里面所有的Operation节点全部写到resOperations里面
     * @param resOperations
     * @param submodelElements
     * @return
     */
    private void operations(HashMap<String, Operation> resOperations, List<SubmodelElement> submodelElements) {
        for (SubmodelElement submodelElement : submodelElements) {
            String elementType = submodelElement.getModelType().getName();
            //如果是Property，就什么也不干
            //如果是Operation，就加入集合中
            if (elementType.equals(ElementType.Operation)) {
                resOperations.put(submodelElement.getIdShort(), (Operation) submodelElement);
            }
            //如果是SEC，就递归遍历
            if (elementType.equals(ElementType.SEC)) {
                List<SubmodelElement> submodelElementList = ((SubmodelElementCollection) submodelElement).getValue();
                operations(resOperations, submodelElementList);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submodel submodel = (Submodel) o;
        return Objects.equals(semanticId, submodel.semanticId) && Objects.equals(identification, submodel.identification) && Objects.equals(idShort, submodel.idShort) && Objects.equals(category, submodel.category) && Objects.equals(modelType, submodel.modelType) && Objects.equals(submodelElements, submodel.submodelElements) && Objects.equals(kind, submodel.kind) && Objects.equals(descriptions, submodel.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semanticId, identification, idShort, category, modelType, submodelElements, kind, descriptions);
    }
}
