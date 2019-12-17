import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeTarifOnWebComponent } from './home-tarif-on-web.component';

describe('HomeTarifOnWebComponent', () => {
  let component: HomeTarifOnWebComponent;
  let fixture: ComponentFixture<HomeTarifOnWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeTarifOnWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeTarifOnWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
