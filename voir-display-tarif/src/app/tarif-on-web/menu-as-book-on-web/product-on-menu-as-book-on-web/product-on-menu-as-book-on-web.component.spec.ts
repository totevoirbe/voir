import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductOnMenuAsBookOnWebComponent } from './product-on-menu-as-book-on-web.component';

describe('ProductOnMenuAsBookOnWebComponent', () => {
  let component: ProductOnMenuAsBookOnWebComponent;
  let fixture: ComponentFixture<ProductOnMenuAsBookOnWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductOnMenuAsBookOnWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductOnMenuAsBookOnWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
