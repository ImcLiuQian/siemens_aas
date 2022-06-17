package com.imc.siemens_aas.aasenv.submodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imc.siemens_aas.aasenv.submodel.metamodel.SemanticId;
import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.Property;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.SubmodelElement;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.SubmodelElementCollection;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.utils.TypeUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


@Data
@JsonIgnoreProperties(value = {"modelObject"})
public class Submodel implements Cloneable{
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
     * 调用根据Message中的interactionElements调用modelObject中的方法
     */
    public void doMethod(List<InteractionElement> interactionElements) {

    }
}
