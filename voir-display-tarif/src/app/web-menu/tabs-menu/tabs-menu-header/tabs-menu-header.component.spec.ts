import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabsMenuHeaderComponent } from './tabs-menu-header.component';

describe('TabsMenuHeaderComponent', () => {
  let component: TabsMenuHeaderComponent;
  let fixture: ComponentFixture<TabsMenuHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabsMenuHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabsMenuHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
