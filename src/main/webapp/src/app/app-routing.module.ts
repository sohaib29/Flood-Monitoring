import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from "./components/homepage/homepage.component";
import {StationComponent} from "./components/station/station.component";
import {StationResolver} from "./resolver/station-resolver";

const routes: Routes = [
  {path: 'station', component: HomepageComponent},
  {
    path: 'station/:id',
    resolve: {stationResolver: StationResolver},
    component: StationComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
