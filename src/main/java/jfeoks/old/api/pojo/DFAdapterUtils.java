package jfeoks.old.api.pojo;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class DFAdapterUtils {

    public static String createKey(Class<?> clz) {
        StringBuilder sb = new StringBuilder();
        sb.append(toGenericString(clz));

        Type[] interfaces = clz.getGenericInterfaces();

        for (Type type: interfaces) {
               sb.
                       append("|").
                       append(type);
        }

        return sb.toString();
    }

    private static String toGenericString(Class<?> clz) {
        if (clz.isPrimitive()) {
            return clz.toString();
        } else {
            StringBuilder sb = new StringBuilder();

            // Class modifiers are a superset of interface modifiers
            int modifiers = clz.getModifiers() & Modifier.classModifiers();
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers));
                sb.append(' ');
            }

            if (clz.isAnnotation()) {
                sb.append('@');
            }
            if (clz.isInterface()) { // Note: all annotation types are interfaces
                sb.append("interface");
            } else {
                if (clz.isEnum())
                    sb.append("enum");
                else
                    sb.append("class");
            }
            sb.append(' ');
            sb.append(clz.getName());

            TypeVariable<?>[] typeparms = clz.getTypeParameters();
            if (typeparms.length > 0) {
                boolean first = true;
                sb.append('<');
                for(TypeVariable<?> typeparm: typeparms) {
                    if (!first)
                        sb.append(',');
                    sb.append(typeparm.getTypeName());
                    first = false;
                }
                sb.append('>');
            }

            return sb.toString();
        }
    }
}
