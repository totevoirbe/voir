import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreenMenuProductComponent } from './screen-menu-product.component';

describe('ScreenMenuProductComponent', () => {
  let component: ScreenMenuProductComponent;
  let fixture: ComponentFixture<ScreenMenuProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScreenMenuProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScreenMenuProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
