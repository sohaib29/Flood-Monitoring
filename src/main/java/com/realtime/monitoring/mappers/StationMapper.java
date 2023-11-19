//package com.realtime.monitoring.mappers;
//
//import com.realtime.monitoring.data.Items;
//import com.realtime.monitoring.data.enums.StationStatus;
//import com.realtime.monitoring.data.frontendResponse.MonitoringStation;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.mapstruct.NullValueCheckStrategy;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//public interface StationMapper {
//
//    @Mapping(target = "id", expression = "java(getIdentifier(source.getId()))")
//    @Mapping(target = "river", source = "source.getRiverName()")
//    @Mapping(target = "status", expression = "java(getStatus(source.getStatus()))")
//    MonitoringStation modelToResource(Items source);
//
//    @Named("getIdentifier")
//    private String getIdentifier(String id) {
//        if (id != null) {
//            return getLastElementUrl(id);
//        }
//        return null;
//    }
//
//    @Named("getStatus")
//    private List<StationStatus> getStatus(List<String> status) {
//        List<StationStatus> stationStatuses = new ArrayList<>();
//        if (status != null) {
//            for (String urlStatus : status) {
//                switch (getLastElementUrl(urlStatus)) {
//                    case "statusActive":
//                        stationStatuses.add(StationStatus.ACTIVE);
//                        break;
//                    case "statusClosed":
//                        stationStatuses.add(StationStatus.CLOSED);
//                        break;
//                    case "statusSuspended":
//                        stationStatuses.add(StationStatus.SUSPENDED);
//                        break;
//                }
//            }
//            return stationStatuses;
//        }
//        return null;
//    }
//
//    private String getLastElementUrl(String urlValue) {
//        String lastSegment = null;
//        try {
//            URL url = new URL(urlValue);
//            String path = url.getPath();
//            String[] pathSegments = path.split("/");
//            lastSegment = pathSegments[pathSegments.length - 1];
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return lastSegment;
//    }
//}
