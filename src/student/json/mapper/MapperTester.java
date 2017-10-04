/*package student.json.mapper;

import java.math.BigDecimal;

public class MapperTester {

private static Mappings getMappings() {

    Mappings mappings = new Mappings();

    final MappingSource REQ_PARAM = MappingSource.REQUEST_PARAMETER;

    mappings.addMapping(REQ_PARAM, "code", "code", String.class, 0, true)//
            .setRequired("label.code", "error.noCode", "");

    mappings.addMapping(REQ_PARAM, "name", "name", String.class, 0, true)//
            .setRequired("label.name", "error.mustEnterName", "");

    mappings.addMapping(REQ_PARAM, "description", "description", String.class, 0, true);

    mappings.addMapping(REQ_PARAM, "sequenceNumber", "sequenceNumber", BigDecimal.class, 0, true)//
            .setRequired("label.sequenceNumber", "error.mustEnterSequenceNumber", "")//
            .setInvalid("label.sequenceNumber", "error.invalid", "label.sequenceNumber");

    mappings.addMapping(REQ_PARAM, "documentModuleId", "documentModuleId", Integer.class, 0, true)//
            .setRequired("label.documentModuleId", "error.mustEnterSequenceNumber", "")//
            .setInvalid("label.documentModuleId", "error.invalid", "label.documentModuleId");
    return mappings;

}

private Mapper getMapper(DynaActionForm dform, MasterDocument masterDocument, ValidationResult validationResult,
        boolean addMode) {
    Login login = getContext().getLogin();
    HttpServletRequest request = getContext().getRequest();
    Mapper mapper = new Mapper(getMappings(), login.getNumberLocale(), login.getDateFormat(), null, dform, request, null,
            masterDocument, validationResult);
    return mapper;
}

private void mapFormToBo(HttpServletRequest request, Login login, DynaActionForm dform, MasterDocument masterDocument,
        ValidationResult vr, boolean addMode) {
    Mapper mapper = getMapper(dform, masterDocument, vr, addMode);
    mapper.mapToBo();

}


}
*/