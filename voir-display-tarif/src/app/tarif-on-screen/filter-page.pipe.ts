import { Pipe, PipeTransform } from '@angular/core';
import { ScreenMenuItem } from '../data-layer/model/screenMenuItem';

@Pipe({
  name: 'filterPage'
})
export class FilterPagePipe implements PipeTransform {

  transform(screenMenuItems: ScreenMenuItem[], pageFilter: string[]): ScreenMenuItem[] {
    console.log(pageFilter);
    return screenMenuItems.filter(screenMenuItem => pageFilter.indexOf(screenMenuItem.page) > -1);
  }

}
