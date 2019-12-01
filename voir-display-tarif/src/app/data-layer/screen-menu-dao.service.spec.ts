import { TestBed } from '@angular/core/testing';

import { ScreenMenuDaoService } from './screen-menu-dao.service';

describe('ScreenMenuDaoService', () => {
  let service: ScreenMenuDaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScreenMenuDaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
