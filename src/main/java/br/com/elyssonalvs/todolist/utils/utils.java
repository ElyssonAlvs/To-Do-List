package br.com.elyssonalvs.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class utils {

  // Método para copiar propriedades não nulas de um objeto de origem para um objeto de destino
  public static void copyNonNullProperties(Object source, Object target) {
    // Usa BeanUtils para copiar propriedades, evitando copiar propriedades nulas
    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }

  // Método para obter nomes de propriedades nulas em um objeto
  public static String[] getNullPropertyNames(Object source) {
    // Cria um invólucro de feijão (BeanWrapper) para o objeto de origem
    final BeanWrapper src = new BeanWrapperImpl(source);

    // Obtém as descrições de propriedades do objeto de origem
    PropertyDescriptor[] pds =  src.getPropertyDescriptors();

    // Cria um conjunto para armazenar os nomes de propriedades nulas
    Set<String> emptyNames = new HashSet<>();
    
    // Percorre as descrições de propriedades
    for(PropertyDescriptor pd: pds) {
      // Obtém o valor da propriedade
      Object srcValue =  src.getPropertyValue(pd.getName());
      // Se o valor for nulo, adiciona o nome da propriedade ao conjunto
      if(srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }
    // Converte o conjunto de nomes de propriedades nulas em um array de strings
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
