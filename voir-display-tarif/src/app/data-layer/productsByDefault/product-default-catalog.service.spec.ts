import { TestBed } from '@angular/core/testing';

import { ProductDefaultCatalogService } from './product-default-catalog.service';

describe('ProductDefaultCatalogService', () => {
  let service: ProductDefaultCatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductDefaultCatalogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
