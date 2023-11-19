export interface MonitoringStations {
  id: string;
  label: string[];
  town: string;
  river: string;
  dateOpened: string[];
  status: status[];
}

export interface MonitoringStation {
  id: string;
  label: string[];
  catchment: string;
  town: string;
  river: string;
  dateOpened: string[];
  status: status[];
  scale: Scale;
  downstreamScale: Scale;
}

export interface Scale {
  highestRecent: Readings;
  maxOnRecord: Readings;
  minOnRecord: Readings;
  scaleMax: number;
  typicalRangeHigh: number[];
  typicalRangeLow: number[];
}

export interface MonitoringStationReadings {
  stageLevel: Readings;
  downStreamStageLevel: Readings;
  groundWaterLevel: Readings
  tidalLevel: Readings;
  windSpeed: Readings;
  windDirection: Readings;
  flow: Readings;
  temperature: Readings;
}

export interface Readings {
  dateTime: string[];
  value: number[];
}

export enum status {
  ACTIVE = 'Active',
  CLOSED = 'Closed',
  SUSPENDED = 'Suspended'
}


