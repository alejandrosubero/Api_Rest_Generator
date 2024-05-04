import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JavaVersionViewComponent } from './java-version-view.component';

describe('JavaVersionViewComponent', () => {
  let component: JavaVersionViewComponent;
  let fixture: ComponentFixture<JavaVersionViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JavaVersionViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JavaVersionViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
