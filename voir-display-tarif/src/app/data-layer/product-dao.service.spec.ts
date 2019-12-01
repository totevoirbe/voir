import { TestBed } from '@angular/core/testing';

import { ProductDaoService } from './product-dao.service';

describe('ProductDaoService', () => {
  let service: ProductDaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductDaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
