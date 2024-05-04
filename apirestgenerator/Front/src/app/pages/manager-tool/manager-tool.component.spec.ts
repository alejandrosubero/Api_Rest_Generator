import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerToolComponent } from './manager-tool.component';

describe('ManagerToolComponent', () => {
  let component: ManagerToolComponent;
  let fixture: ComponentFixture<ManagerToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagerToolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
