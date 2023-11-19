export interface MonitoringStations {
  id: string;
  label: string[];
  town: string;
  river: string;
  dateOpened: string[];
  status: status[];
}

export enum status {
  ACTIVE = 'Active',
  CLOSED = 'Closed',
  SUSPENDED = 'Suspended'
}

export enum stationType {
  SINGLE_LEVEL = 'Single Level',
  MULTI_LEVEL = 'Multi Level'
}
