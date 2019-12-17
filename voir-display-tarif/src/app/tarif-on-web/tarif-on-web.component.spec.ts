import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TarifOnWebComponent } from './tarif-on-web.component';

describe('TarifOnWebComponent', () => {
  let component: TarifOnWebComponent;
  let fixture: ComponentFixture<TarifOnWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarifOnWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TarifOnWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
